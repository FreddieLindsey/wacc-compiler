package wacc.ast.function;

import wacc.Main;
import wacc.ast.ASTNode;
import wacc.ast.IdentNode;
import wacc.ast.io.StatNode;
import wacc.ast.type.FuncTypeNode;
import wacc.ast.type.TypeNode;
import wacc.backend.instruction.InstructionBlock;
import wacc.symbolTable.SymbolTable;

public class FuncNode extends ASTNode {

  private FuncTypeNode t;
  private IdentNode n;
  private ParamListNode ps;
  private StatNode stat;

  public FuncNode(TypeNode t, IdentNode n, StatNode stat) {
    super();
    this.t = new FuncTypeNode(t);
    this.n = n;
    this.ps = new ParamListNode();
    this.stat = stat;
    t.setParent(this);
    n.setParent(this);
    ps.setParent(this);
    stat.setParent(this);
  }

  public void addParam(ParamNode p) {
    ps.addParam(p);
    t.addParamType(p.getType());
  }

  public TypeNode getType() {
    return this.t;
  }

  public IdentNode getIdent() {
    return this.n;
  }

  public ParamListNode getParams() {
    return this.ps;
  }

  public StatNode getStat() {
    return this.stat;
  }

  @Override
  public boolean isSemanticallyValid() {
    SymbolTable s = new SymbolTable();
    for (ParamNode p : ps.getParams()) {
      if (s.lookUpType(p.getIdent().getIdent()) != null) {
        return false;
      }
      s.add(p.getIdent().getIdent(), p.getType());
    }

    // Check the type is valid
    if (!t.isSemanticallyValid()) {
      return false;
    }

    // Check the type is valid
    if (!n.isSemanticallyValid()) {
      return false;
    }

    // Check the type is valid
    if (!ps.isSemanticallyValid()) {
      return false;
    }

    // Add the params to the symbolTable for stats to use
    for (ParamNode p : ps.getParams()) {
      symbolTable.add(p.getIdent().getIdent(), p.getType());
    }

    // Check whether the function returns
    if (!stat.returns()) {
      System.exit(Main.SYNTAX_EXIT);
    }

    // Check the type is valid
    if (!stat.isSemanticallyValid()) {
      return false;
    }

    // Check the function has a valid return
    if (!(t.getReturnType().equals(stat.returnType()))) {
      return false;
    }

    semanticallyValid = true;
    return semanticallyValid;
  }

  public InstructionBlock generateCode() {
    InstructionBlock i = new InstructionBlock();

//     According to wiki, this is the ARM calling convention:
//     In the prologue, push r4 to r11 to the stack, and push the return address in r14, to the stack. (This can be done with a single STM instruction).
//     copy any passed arguments (in r0 to r3) to the local scratch registers (r4 to r11).
//     allocate other local variables to the remaining local scratch registers (r4 to r11).
//     do calculations and call other subroutines as necessary using BL, assuming r0 to r3, r12 and r14 will not be preserved.
//     put the result in r0
//     In the epilogue, pull r4 to r11 from the stack, and pull the return address to the program counter r15. (This can be done with a single LDM instruction).

    i.add(stat.generateCode());

    return i;
  }

}
