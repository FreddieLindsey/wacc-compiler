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
    
    // ArrayList containting only r4
    ArrayList<Register> r4List = new ArrayList<>();
    r4List.add(new Register(RegEnum.R4));

    // STR r4, [sp]
    // Used by multiple cases to store a word on the stack
    ArrayList<Arg> wordStoreArgs = new ArrayList<>();
    wordStoreArgs.add(new Register(RegEnum.R4));
    wordStoreArgs.add(new MemoryAccess(new Register(RegEnum.SP)));
    AssemblyInstr wordStore =
        new AssemblyInstr(AssemblyInstrEnum.STR, AssemblyInstrCond.NO_CODE,
            wordStoreArgs);

    // TODO: string case requires creating a string label, are we
    // pre-processing for that?

    switch (t.getType()) {
    case PAIR:
      /*
       *  Code from ref compiler:
       *  SUB sp, sp, #4
       *  LDR r0, =8 // size required in memory (word for each address)
       *  BL malloc  // convert r0 to a memory address with corresponding size
       *  MOV r4, r0 // save allocated address to r4
       *  LDR r5, =x / MOV r5, #value / etc.
       *  LDR r0, =size (e.g. byte = 1, word = 4)
       *  BL malloc
       *  STR(B) r5, [r0] // store value in r5 in new memory address
       *  STR r0, [r4]    // store address of r5 in initially allocated addr
       *  LDR r5, =x / MOV r5, #value / etc.
       *  LDR r0, =size (e.g. byte = 1, word = 4)
       *  BL malloc
       *  STR(B) r5, [r0]
       *  STR r0, [r4, #4] // store next address 1 word along
       *  STR r4, [sp]
       *  ADD sp, sp, #4
       */

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
      ArrayList<Arg> mallocArgs = new ArrayList<>();
      mallocArgs.add(new Label("malloc"));
      i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
          AssemblyInstrCond.NO_CODE, mallocArgs));

      // Save address allocated to r0 in r4 to reuse r0 for another malloc
      // MOV r4, r0
      ArrayList<Arg> saveAddressArgs = new ArrayList<>();
      saveAddressArgs.add(new Register(RegEnum.R4));
      saveAddressArgs.add(new Register(RegEnum.R0));
      i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
          AssemblyInstrCond.NO_CODE, saveAddressArgs));
      
      // Set r5 to the value of the first element in the new pair
      // LDR r5, =x / MOV r5, #value / etc.
      // TODO: This will be tricky with how we've done pair types.
      // I'll come back to it later..
      
      // Set r0 to the space in bytes required to hold the first element
      // LDR r0, =size (e.g. byte = 1, word = 4)
      // TODO: Also tricky, but less so. Again, will come back to this..
      
      // Allocates memory for first element
      // BL malloc
      i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
          AssemblyInstrCond.NO_CODE, mallocArgs));
      
      // Store the value of the first element in the memory address allocated
      // STR(B) r5, [r0]
      // TODO: Again, a pain since knowledge of the element size is needed..
      
      // Store the address of the first element in the first word of the
      // pair address (held in r4)
      // STR r0, [r4]
      ArrayList<Arg> firstElemStoreArgs = new ArrayList<>();
      firstElemStoreArgs.add(new Register(RegEnum.R0));
      firstElemStoreArgs.add(new MemoryAccess(new Register(RegEnum.R4)));
      i.add(new AssemblyInstr(AssemblyInstrEnum.STR,
          AssemblyInstrCond.NO_CODE, firstElemStoreArgs));
      
      // Set r5 to the value of the second element in the new pair
      // LDR r5, =x / MOV r5, #value / etc.
      // TODO: Still tricky..
      
      // Set r0 to the space in bytes required to hold the first element
      // LDR r0, =size (e.g. byte = 1, word = 4)
      // TODO: You guessed it..
      
      // Allocates memory for the second element
      // BL malloc
      i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
          AssemblyInstrCond.NO_CODE, mallocArgs));
      
      // Store the value of the second element in the memory address allocated
      // STR(B) r5, [r0]
      // TODO: Just as much of a pain as for the first element..
      
      // Store the address of the second element in the second word of the
      // pair address (held in r4)
      // STR r0, [r4, #4]
      // TODO: Not sure if we support this sort of memory access instruction,
      // will come back to this..
      
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
      loadValue(new Register(RegEnum.R4));

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
      loadValue(new Register(RegEnum.R4));

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
      loadValue(new Register(RegEnum.R4));

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
  
  private InstructionBlock loadValue(Register reg) {
    ArrayList<Register> regList = new ArrayList<>();
    regList.add(reg);
    if (rhs instanceof IntNode) {
      return ((IntNode) rhs).generateCode(regList);
    } else if (rhs instanceof StringNode) {
      // TODO: StringNode generateCode currently unimplemented
      //return ((StringNode) rhs).generateCode(regList);
      return null;
    } else if (rhs instanceof CharNode) {
      return ((CharNode) rhs).generateCode(regList);
    } else if (rhs instanceof BoolNode) {
      return ((BoolNode) rhs).generateCode(regList);
    } else {
      System.err.println("Error in loadValue in NewAssignNode.");
      return null;
    }
  }

}
