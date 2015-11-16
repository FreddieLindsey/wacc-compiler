package wacc.ast;

public class FuncNode implements ASTNode {

  private TypeNode t;
  private IdentNode n;
  private ParamListNode ps;
  private StatNode stat;

  public FuncNode(TypeNode t, IdentNode n, ParamListNode ps, StatNode stat) {
    this.t = t;
    this.n = n;
    this.ps = ps;
    this.stat = stat;
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
