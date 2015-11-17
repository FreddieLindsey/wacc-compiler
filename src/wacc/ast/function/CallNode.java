package wacc.ast.function;

import wacc.ast.type.TypeNode;
import wacc.ast.ASTNode;
import wacc.ast.AssignNode;
import wacc.ast.ExprNode;
import wacc.ast.IdentNode;
import wacc.ast.type.TypeEnum;

public class CallNode extends AssignNode {

  private IdentNode ident;
  private ArgListNode args;

  public CallNode(IdentNode ident) {
    super();
    this.ident = ident;
    this.args = new ArgListNode();
    ident.setParent(this);
    this.args.setParent(this);
  }

  public void addArg(ExprNode e) {
    args.addExpr(e);

    // ERROR if function trying to be called doesn't exist in symbol table
    requireSymbol(ident.getIdent());
  }

  public IdentNode getIdent() {
    return this.ident;
  }

  public ArgListNode getArgs() {
    return this.args;
  }

  @Override
  public TypeNode type() {
    // We only care about the return type of the function being called,
    // which is included in the IdentNode assigned to the object
    return ident.type();
  }

  @Override
  public boolean isSemanticallyValid() {
    return ident.isSemanticallyValid()
      && args.isSemanticallyValid();
  }

  @Override
  public boolean validLeft() {
    return false;
  }

  @Override
  public boolean validRight() {
    return true;
  }

}
