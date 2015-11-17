package wacc.ast.function;

import wacc.ast.ASTNode;
import wacc.ast.IdentNode;
import wacc.ast.StatNode;
import wacc.ast.type.TypeNode;
import wacc.symbolTable.SymbolTable;

public class FuncNode extends ASTNode {

  private TypeNode t;
  private IdentNode n;
  private ParamListNode ps;
  private StatNode stat;
  private SymbolTable scope;

  public FuncNode(TypeNode t, IdentNode n, StatNode stat) {
    super();
    this.t = t;
    this.n = n;
    this.ps = new ParamListNode();
    this.stat = stat;
    t.setParent(this);
    n.setParent(this);
    this.ps.setParent(this);
    stat.setParent(this);
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
  
  public void setScope(SymbolTable st) {
    this.scope = st;
  }

  @Override
  public boolean isSemanticallyValid() {
    return t.isSemanticallyValid()
        && n.isSemanticallyValid()
        && ps.isSemanticallyValid()
        && stat.isSemanticallyValid();
  }

}
