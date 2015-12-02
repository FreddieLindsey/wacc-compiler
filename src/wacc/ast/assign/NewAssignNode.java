package wacc.ast.assign;

import java.util.ArrayList;

import wacc.ast.ExprNode;
import wacc.ast.IdentNode;
import wacc.ast.function.CallNode;
import wacc.ast.io.StatNode;
import wacc.ast.pair.NewPairNode;
import wacc.ast.type.AnyTypeNode;
import wacc.ast.type.FuncTypeNode;
import wacc.ast.type.PairTypeNode;
import wacc.ast.type.TypeNode;
import wacc.backend.instruction.Arg;
import wacc.backend.instruction.AssemblyInstr;
import wacc.backend.instruction.AssemblyInstrCond;
import wacc.backend.instruction.AssemblyInstrEnum;
import wacc.backend.instruction.Const;
import wacc.backend.instruction.InstructionBlock;
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

    // Get the type of the new statement
    TypeNode returnType;
    if (rhs instanceof CallNode) {
      returnType = ((FuncTypeNode) symbolTable.lookUp(((CallNode) rhs)
          .getIdent().getIdent())).getReturnType();
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

    switch (t.getType()) {
    case BOOL:
    case CHAR:
      // bool and char cases the same, both 1 byte
      
      // Move the stack pointer down by 1 byte (stack grows downwards)
      // SUB sp, sp, #1
      ArrayList<Arg> byteSubArgs = new ArrayList<>();
      byteSubArgs.add(new Register(RegEnum.SP));
      byteSubArgs.add(new Register(RegEnum.SP));
      byteSubArgs.add(new Const(1, true));
      i.add(new AssemblyInstr(AssemblyInstrEnum.SUB,
          AssemblyInstrCond.NO_CODE, byteSubArgs));
      
      // Set the register up with the rhs value
      // MOV r4, #value
      
      // Store the signle byte with the value on the stack
      // STRB r4, [sp]
      
      // Restore stack pointer up 1 byte
      // ADD sp, sp, #1
    }

    // TODO
    // evaluate rhs, leaves result in a register/finds memory addr
    // instrs.addAll(rhs.generateCode());

    // TODO: move register val to memory
    // instrs.addAll()

    // TODO: make ident point to memory addr

    return i;
  }

}
