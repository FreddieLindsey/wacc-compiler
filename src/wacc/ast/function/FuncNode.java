package wacc.ast.function;

import wacc.ast.ASTNode;
import wacc.ast.IdentNode;
import wacc.ast.StatNode;
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

    // TODO: Check if stat contains a return

    return t.isSemanticallyValid()
        && n.isSemanticallyValid()
        && ps.isSemanticallyValid()
        && stat.isSemanticallyValid();
  }

}
