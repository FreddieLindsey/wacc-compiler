package wacc.ast.function;

import wacc.Main;
import wacc.ast.ASTNode;
import wacc.ast.IdentNode;
import wacc.ast.io.StatNode;
import wacc.ast.type.FuncTypeNode;
import wacc.ast.type.TypeNode;
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
      if (s.lookUp(p.getIdent().getIdent()) != null) {
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

}
