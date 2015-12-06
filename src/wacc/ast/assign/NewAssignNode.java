package wacc.ast.assign;

import wacc.ast.ExprNode;
import wacc.ast.IdentNode;
import wacc.ast.function.CallNode;
import wacc.ast.io.StatNode;
import wacc.ast.pair.NewPairNode;
import wacc.ast.type.*;
import wacc.backend.instruction.*;

import java.util.ArrayList;

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
    // Check that the identifier being created doesn't already exist in this scope
    if (parent.getSymbolTable().lookUpHere(i.getIdent()) != null) return false;

    // Get the type of the new statement
    TypeNode returnType;
    if (rhs instanceof CallNode) {
      returnType = (
        (FuncTypeNode) symbolTable.lookUp(
          ((CallNode) rhs).getIdent().getIdent())
      ).getReturnType();
    } else if (rhs instanceof NewPairNode) {
      ExprNode fst = ((NewPairNode) rhs).getFst();
      ExprNode snd = ((NewPairNode) rhs).getSnd();
      TypeNode fstType, sndType;


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

      returnType = (
        new PairTypeNode(fstType, sndType));
    } else {
      returnType = rhs.type();
    }

    // Return false if the ident isn't valid
    if (!i.isSemanticallyValid()) return false;

    // Check the rhs is valid for the right
    if (!rhs.validRight()) return false;

    // Check the rhs is semantically valid
    if (!rhs.isSemanticallyValid()) return false;

    // Check that the type of the new node and the given type are equal
    if (!(returnType.equals(t) || t.equals(returnType))) return false;

    addToScope(i.getIdent(), t);

    semanticallyValid = true;
    return semanticallyValid;
  }

  @Override
  public ArrayList<Instruction> generateCode() {
    ArrayList<Instruction> instrs = new ArrayList<Instruction>();

    ArrayList<Arg> args;
    AssemblyInstr a;

    // SUB sp, sp, #4
    args = new ArrayList<Arg>();
    args.add(new Register(RegEnum.SP));
    args.add(new Register(RegEnum.SP));
    args.add(new Const(4, true));
    a = new AssemblyInstr(AssemblyInstrEnum.SUB,
      AssemblyInstrCond.NO_CODE, args);
    instrs.add(a);

    // Find code for all types
    switch (rhs.type().getType()) {
      case INT:
        // LDR r4, =19
        long exitCode = ((IntNode) rhs).getValue();
        args = new ArrayList<Arg>();
        args.add(new Register(RegEnum.R4));
        args.add(new Const((int) exitCode, false));
        a = new AssemblyInstr(AssemblyInstrEnum.LDR,
          AssemblyInstrCond.NO_CODE, args);
        instrs.add(a);
    }

    // STR r4, [sp]
    args = new ArrayList<Arg>();
    args.add(new Register(RegEnum.R4));
    args.add(new Register(RegEnum.SP));
    a = new AssemblyInstr(AssemblyInstrEnum.STR,
      AssemblyInstrCond.NO_CODE, args);
    instrs.add(a);

    return instrs;
  }

}
