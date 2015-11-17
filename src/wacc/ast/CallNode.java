package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class CallNode extends ExprNode {

  private IdentNode ident;
  private ArgListNode args;

  public CallNode(IdentNode ident, ArgListNode args) {
    super();
    this.ident = ident;
    this.args = args;
    ident.setParent(this);
    args.setParent(this);
  }

  public IdentNode getIdent() {
    return this.ident;
  }

  public ArgListNode getArgs() {
    return this.args;
  }

  @Override
  public TypeEnum type() {
    // TODO: Symbol Table
    return null;
  }

  @Override
  public boolean isSemanticallyValid() {
    return ident.isSemanticallyValid()
      && args.isSemanticallyValid();
  }

}
