package wacc.ast.function;

import wacc.ast.ASTNode;
import wacc.ast.IdentNode;
import wacc.ast.StatNode;
import wacc.ast.type.TypeNode;

public class FuncNode extends ASTNode {

  private TypeNode t;
  private IdentNode n;
  private ParamListNode ps;
  private StatNode stat;

  public FuncNode(TypeNode t, IdentNode n, StatNode stat) {
    super();
    this.t = t;
    this.n = n;
    this.ps = new ParamListNode();
    this.stat = stat;
    t.setParent(this);
    n.setParent(this);
    ps.setParent(this);
    stat.setParent(this);
    for (ParamNode p : ps.getParams()) {
      symbolTable.add(p.getIdent().getIdent(), p.getType());
    }
  }

  public void addParam(ParamNode p) {
    ps.addParam(p);
    p.setParent(ps);

    // ERROR if already exists in symbol table
    checkSymbolTable(n.getIdent());
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
    return t.isSemanticallyValid()
        && n.isSemanticallyValid()
        && ps.isSemanticallyValid()
        && stat.isSemanticallyValid();
  }

}
