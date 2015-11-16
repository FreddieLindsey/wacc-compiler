package wacc.ast.function;

<<<<<<< 13bee15090abd00d60efafe016aec785e7f780af:src/wacc/ast/CallNode.java
import wacc.ast.type.TypeNode;
=======
import wacc.ast.ASTNode;
import wacc.ast.ExprNode;
import wacc.ast.IdentNode;
import wacc.ast.type.TypeEnum;
>>>>>>> Slight refactor:src/wacc/ast/function/CallNode.java

public class CallNode extends ExprNode {

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
  }

  public IdentNode getIdent() {
    return this.ident;
  }

  public ArgListNode getArgs() {
    return this.args;
  }

  @Override
  public TypeNode type() {
    // TODO: Symbol Table
    return null;
  }

  @Override
  public boolean isSemanticallyValid() {
    return ident.isSemanticallyValid()
      && args.isSemanticallyValid();
  }

}
