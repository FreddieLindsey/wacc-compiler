package wacc.ast.assign;

import java.util.ArrayList;

import wacc.ast.ExprNode;
import wacc.ast.IdentNode;
import wacc.ast.function.CallNode;
import wacc.ast.io.StatNode;
import wacc.ast.pair.NewPairNode;
import wacc.ast.type.AnyTypeNode;
import wacc.ast.type.BoolNode;
import wacc.ast.type.CharNode;
import wacc.ast.type.FuncTypeNode;
import wacc.ast.type.IntNode;
import wacc.ast.type.PairTypeNode;
import wacc.ast.type.StringNode;
import wacc.ast.type.TypeNode;
import wacc.backend.instruction.Arg;
import wacc.backend.instruction.AssemblyInstr;
import wacc.backend.instruction.AssemblyInstrCond;
import wacc.backend.instruction.AssemblyInstrEnum;
import wacc.backend.instruction.Const;
import wacc.backend.instruction.Instruction;
import wacc.backend.instruction.InstructionBlock;
import wacc.backend.instruction.Label;
import wacc.backend.instruction.MemoryAccess;
import wacc.backend.instruction.RegEnum;
import wacc.backend.instruction.Register;

public class NewAssignNode extends StatNode {

  private TypeNode t;
  private IdentNode i;
  private AssignNode rhs;

  public NewAssignNode(TypeNode t, IdentNode i, AssignNode rhs) {
    super();
    this.t = t;
    this.i = i;
    this.rhs = rhs;
    i.setParent(this);
    rhs.setParent(this);
  }

  public TypeNode getType() {
    return t;
  }

  public IdentNode getIdent() {
    return i;
  }

  public AssignNode getRHS() {
    return rhs;
  }

  @Override
  public boolean isSemanticallyValid() {
    // Check that the identifier being created doesn't already exist in this
    // scope
    if (parent.getSymbolTable().lookUpHere(i.getIdent()) != null)
      return false;

    // Get the type of the rhs
    TypeNode returnType;
    if (rhs instanceof CallNode) {
      returnType =
          ((FuncTypeNode) symbolTable.lookUp(((CallNode) rhs).getIdent()
              .getIdent())).getReturnType();
    } else if (rhs instanceof NewPairNode) {
      ExprNode fst = ((NewPairNode) rhs).getFst();
      ExprNode snd = ((NewPairNode) rhs).getSnd();
      TypeNode fstType, sndType;

      // Get type of first element
      if (fst instanceof IdentNode) {
        TypeNode t = symbolTable.lookUp(((IdentNode) fst).getIdent());
        if (t instanceof PairTypeNode) {
          fstType = new PairTypeNode(new AnyTypeNode(), new AnyTypeNode());
        } else {
          fstType = t;
        }
      } else {
        fstType = fst.type();
      }

      // Get type of second element
      if (snd instanceof IdentNode) {
        TypeNode t = symbolTable.lookUp(((IdentNode) snd).getIdent());
        if (t instanceof PairTypeNode) {
          sndType = new PairTypeNode();
        } else {
          sndType = t;
        }
      } else {
        sndType = snd.type();
      }

      // Set type to corresponding pair type
      returnType = (new PairTypeNode(fstType, sndType));
    } else {
      returnType = rhs.type();
    }

    // Return false if the ident isn't valid
    if (!i.isSemanticallyValid())
      return false;

    // Check the rhs is valid for the right
    if (!rhs.validRight())
      return false;

    // Check the rhs is semantically valid
    if (!rhs.isSemanticallyValid())
      return false;

    // Check that the type of the new node and the given type are equal
    if (!(returnType.equals(t) || t.equals(returnType)))
      return false;

    // Add new ident to scope
    addToScope(i.getIdent(), t);

    // Valid if not returned false from method
    semanticallyValid = true;
    return semanticallyValid;
  }

  @Override
  public InstructionBlock generateCode() {
    InstructionBlock i = new InstructionBlock();
    /*
     * TODO: In the reference compiler there seems to be some lookahead on
     * multiple declarations, and the stack pointer is decremented by the total
     * amount needed initially then each variable increments it back gradually.
     * This could be considered as an optimisation for the extension.
     */
    
    // Used by multiple cases to store a word on the stack
    // STR r4, [sp]
    ArrayList<Arg> wordStoreArgs = new ArrayList<>();
    wordStoreArgs.add(new Register(RegEnum.R4));
    wordStoreArgs.add(new MemoryAccess(new Register(RegEnum.SP)));
    AssemblyInstr wordStore =
        new AssemblyInstr(AssemblyInstrEnum.STR, AssemblyInstrCond.NO_CODE,
            wordStoreArgs);
    
    // Used by multiple cases to allocate heap memory
    // BL malloc
    ArrayList<Arg> mallocArgs = new ArrayList<>();
    mallocArgs.add(new Label("malloc"));
    AssemblyInstr malloc = new AssemblyInstr(AssemblyInstrEnum.BL,
        AssemblyInstrCond.NO_CODE, mallocArgs);
    
    // Used by multiple cases to save an address returned from malloc
    // MOV r4, r0
    ArrayList<Arg> saveAddressArgs = new ArrayList<>();
    saveAddressArgs.add(new Register(RegEnum.R4));
    saveAddressArgs.add(new Register(RegEnum.R0));
    AssemblyInstr saveAddress = new AssemblyInstr(AssemblyInstrEnum.MOV,
        AssemblyInstrCond.NO_CODE, saveAddressArgs);

    // TODO: string case requires creating a string label, are we
    // pre-processing for that?

    switch (t.getType()) {
    case ARR:
      /*
       * SUB sp, sp, #4
       * LDR r0, =12 // elem size * num elems + 4 (to hold int size)
       * BL  malloc
       * MOV r4, r0 // save allocated address
       * LDR r5, =3 // load first element
       * STR r5, [r4, #4] // store first element
       * LDR r5, =4 // load next element
       * STR r5, [r4, #8] // address + size
       * LDR r5, =2 // number of elements
       * STR r5, [r4] // puts number of elements in address
       * STR r4, [sp] // saves the address onto the stack
       * ADD sp, sp, #4
       */
      
      // Move the stack pointer down by 4 bytes (word)
      // SUB sp, sp, #4
      i.add(decStackPointer(4));
      
      // Set r0 to the space in bytes required to hold n elements of size s,
      // with an additional byte to hold the value s
      // LDR r0, =s * n + 4
      // TODO
      
      // Returns in r0 a memory address with enough space to hold the number
      // of bytes specified by the previous r0 value
      // BL malloc
      i.add(malloc);
      
      // Save address allocated to r0 in r4 to reuse r0 for another malloc
      // MOV r4, r0
      i.add(saveAddress);
      
      // LDR r5, x ; STR r5, [r4, #i * s]
      // TODO: for loop to load and store elements
      
      // Loads the number of elements n
      // LDR r5, =n
      // TODO
      
      // Stores n in the memory address of the array
      // STR r5, [r4]
      ArrayList<Arg> sizeAddressStoreArgs = new ArrayList<>();
      sizeAddressStoreArgs.add(new Register(RegEnum.R5));
      sizeAddressStoreArgs.add(new MemoryAccess(new Register(RegEnum.R4)));
      i.add(new AssemblyInstr(AssemblyInstrEnum.STR,
          AssemblyInstrCond.NO_CODE, sizeAddressStoreArgs));
      
      // Saves the address of the array on the stack
      // STR r4, [sp]
      i.add(wordStore);
      
      // TODO: this should happen when the variable goes out of scope
      // Restore stack pointer up
      // ADD sp, sp, #4
      i.add(incStackPointer(4));
      break;
      
    case PAIR:
      // TODO: Nested pairs
      
      // Gives an ExprNode for each pair element
      if (!(rhs instanceof NewPairNode)) {
        System.err.println("Error in Pair case in NewAssignNode");
      }
      ExprNode fst = ((NewPairNode) rhs).getFst();
      ExprNode snd = ((NewPairNode) rhs).getSnd();
      
      // Booleans determining whether fst and snd are byte sized,
      // i.e. can be stored using STRB
      boolean isFstByte = false;
      boolean isSndByte = false;
      if (fst instanceof CharNode || fst instanceof BoolNode) {
        isFstByte = true;
      }
      if (snd instanceof CharNode || snd instanceof BoolNode) {
        isSndByte = true;
      }

      // Move the stack pointer down by 4 bytes (word)
      // SUB sp, sp, #4
      i.add(decStackPointer(4));
      
      // Set r0 to the space in bytes required to hold two addresses (pair)
      // LDR r0, =8
      ArrayList<Arg> loadPairAddressesArgs = new ArrayList<>();
      loadPairAddressesArgs.add(new Register(RegEnum.R0));
      loadPairAddressesArgs.add(new Const(8, false));
      i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
          AssemblyInstrCond.NO_CODE, loadPairAddressesArgs));

      // Returns in r0 a memory address with enough space to hold the number
      // of bytes specified by the previous r0 value
      // BL malloc
      i.add(malloc);

      // Save address allocated to r0 in r4 to reuse r0 for another malloc
      // MOV r4, r0
      i.add(saveAddress);
      
      // Set r5 to the value of the first element in the new pair
      // LDR r5, =x / MOV r5, #value / etc.
      i.addAll(loadValue(fst, new Register(RegEnum.R5)));
      
      // Set r0 to the space in bytes required to hold the first element
      // LDR r0, =size (e.g. byte = 1, word = 4)
      ArrayList<Arg> firstAddressLoadArgs = new ArrayList<>();
      firstAddressLoadArgs.add(new Register(RegEnum.R0));
      if (isFstByte) {
        firstAddressLoadArgs.add(new Const(1, false));
      } else {
        firstAddressLoadArgs.add(new Const(4, false));
      }
      i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
          AssemblyInstrCond.NO_CODE, firstAddressLoadArgs));
      
      // Allocates memory for first element
      // BL malloc
      i.add(malloc);
      
      // Store the value of the first element in the memory address allocated
      // STR(B) r5, [r0]
      ArrayList<Arg> firstElemStoreArgs = new ArrayList<>();
      firstElemStoreArgs.add(new Register(RegEnum.R5));
      firstElemStoreArgs.add(new MemoryAccess(new Register(RegEnum.R0)));
      if (isFstByte) {
        i.add(new AssemblyInstr(AssemblyInstrEnum.STRB,
            AssemblyInstrCond.NO_CODE, firstElemStoreArgs));
      } else {
        i.add(new AssemblyInstr(AssemblyInstrEnum.STR,
            AssemblyInstrCond.NO_CODE, firstElemStoreArgs));
      }
      
      // Store the address of the first element in the first word of the
      // pair address (held in r4)
      // STR r0, [r4]
      ArrayList<Arg> firstAddressStoreArgs = new ArrayList<>();
      firstAddressStoreArgs.add(new Register(RegEnum.R0));
      firstAddressStoreArgs.add(new MemoryAccess(new Register(RegEnum.R4)));
      i.add(new AssemblyInstr(AssemblyInstrEnum.STR,
          AssemblyInstrCond.NO_CODE, firstAddressStoreArgs));
      
      // Set r5 to the value of the second element in the new pair
      // LDR r5, =x / MOV r5, #value / etc.
      i.addAll(loadValue(snd, new Register(RegEnum.R5)));
      
      // Set r0 to the space in bytes required to hold the first element
      // LDR r0, =size (e.g. byte = 1, word = 4)
      ArrayList<Arg> secondAddressLoadArgs = new ArrayList<>();
      secondAddressLoadArgs.add(new Register(RegEnum.R0));
      if (isSndByte) {
        secondAddressLoadArgs.add(new Const(1, false));
      } else {
        secondAddressLoadArgs.add(new Const(4, false));
      }
      i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
          AssemblyInstrCond.NO_CODE, secondAddressLoadArgs));
      
      // Allocates memory for the second element
      // BL malloc
      i.add(malloc);
      
      // Store the value of the second element in the memory address allocated
      // STR(B) r5, [r0]
      ArrayList<Arg> secondElemStoreArgs = new ArrayList<>();
      secondElemStoreArgs.add(new Register(RegEnum.R5));
      secondElemStoreArgs.add(new MemoryAccess(new Register(RegEnum.R0)));
      if (isSndByte) {
        i.add(new AssemblyInstr(AssemblyInstrEnum.STRB,
            AssemblyInstrCond.NO_CODE, secondElemStoreArgs));
      } else {
        i.add(new AssemblyInstr(AssemblyInstrEnum.STR,
            AssemblyInstrCond.NO_CODE, secondElemStoreArgs));
      }
      
      // Store the address of the second element in the second word of the
      // pair address (held in r4)
      // STR r0, [r4, #4]
      ArrayList<Arg> secondAddressStoreArgs = new ArrayList<>();
      secondAddressStoreArgs.add(new Register(RegEnum.R0));
      ArrayList<Arg> secondAddressStoreMemAccessArgs = new ArrayList<>();
      secondAddressStoreMemAccessArgs.add(new Register(RegEnum.R4));
      secondAddressStoreMemAccessArgs.add(new Const(4, true));
      secondAddressStoreArgs.add(new MemoryAccess(
          secondAddressStoreMemAccessArgs));
      i.add(new AssemblyInstr(AssemblyInstrEnum.STR,
          AssemblyInstrCond.NO_CODE, secondAddressStoreArgs));
      
      // Stores the memory address of the pair on the stack
      // STR r4, [sp]
      i.add(wordStore);
      
      // TODO: this should happen when the variable goes out of scope
      // Restore stack pointer up
      // ADD sp, sp, #4
      i.add(incStackPointer(4));
      break;
      
    case STRING:
      // Move the stack pointer down by 4 bytes (word)
      // SUB sp, sp, #4
      i.add(decStackPointer(4));

      // Load the register with the string at the generated label
      // LDR r4, =label
      i.addAll(loadValue(rhs, new Register(RegEnum.R4)));

      // STR r4, [sp]
      i.add(wordStore);

      // TODO: this should happen when the variable goes out of scope
      // Restore stack pointer up
      // ADD sp, sp, #4
      i.add(incStackPointer(4));
      break;
      
    case INT:
      // Move the stack pointer down by 4 bytes (word)
      // SUB sp, sp, #4
      i.add(decStackPointer(4));

      // Load the register with the appropriate int value
      // LDR r4, =x
      i.addAll(loadValue(rhs, new Register(RegEnum.R4)));

      // STR r4, [sp]
      i.add(wordStore);

      // TODO: this should happen when the variable goes out of scope
      // Restore stack pointer up
      // ADD sp, sp, #4
      i.add(incStackPointer(4));
      break;
      
    case BOOL: // bool and char cases the same, both 1 byte
    case CHAR:
      // Move the stack pointer down by 1 byte
      // SUB sp, sp, #1
      i.add(decStackPointer(1));

      // MOV r4, #value
      i.addAll(loadValue(rhs, new Register(RegEnum.R4)));

      // Store the single byte with the value on the stack
      // STRB r4, [sp]
      ArrayList<Arg> byteStoreArgs = new ArrayList<>();
      byteStoreArgs.add(new Register(RegEnum.R4));
      byteStoreArgs.add(new MemoryAccess(new Register(RegEnum.SP)));
      i.add(new AssemblyInstr(AssemblyInstrEnum.STRB,
          AssemblyInstrCond.NO_CODE, byteStoreArgs));

      // TODO: this should happen when the variable goes out of scope
      // Restore stack pointer up
      // ADD sp, sp, #1
      i.add(incStackPointer(1));
      break;
    }

    return i;
  }

  // Move stack pointer down (stack grows down)
  // SUB sp, sp, #bytes
  private Instruction decStackPointer(int bytes) {
    ArrayList<Arg> args = new ArrayList<>();
    args.add(new Register(RegEnum.SP));
    args.add(new Register(RegEnum.SP));
    args.add(new Const(bytes, true));
    return new AssemblyInstr(AssemblyInstrEnum.SUB, AssemblyInstrCond.NO_CODE,
        args);
  }

  // Restore stack pointer when variable leaves scope
  // ADD sp, sp, #bytes
  private Instruction incStackPointer(int bytes) {
    ArrayList<Arg> args = new ArrayList<>();
    args.add(new Register(RegEnum.SP));
    args.add(new Register(RegEnum.SP));
    args.add(new Const(bytes, true));
    return new AssemblyInstr(AssemblyInstrEnum.ADD, AssemblyInstrCond.NO_CODE,
        args);
  }
  
  private InstructionBlock loadValue(ExprNode expr, Register reg) {
    ArrayList<Register> regList = new ArrayList<>();
    regList.add(reg);
    if (expr instanceof IntNode) {
      return ((IntNode) expr).generateCode(regList);
    } else if (expr instanceof StringNode) {
      // TODO: StringNode generateCode currently unimplemented
      // return ((StringNode) expr).generateCode(regList);
      return null;
    } else if (expr instanceof CharNode) {
      return ((CharNode) expr).generateCode(regList);
    } else if (expr instanceof BoolNode) {
      return ((BoolNode) expr).generateCode(regList);
    } else {
      System.err.println("Error in loadValue in NewAssignNode.");
      return null;
    }
  }

}
