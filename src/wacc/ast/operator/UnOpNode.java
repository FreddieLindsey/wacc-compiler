package wacc.ast.operator;

<<<<<<< 13bee15090abd00d60efafe016aec785e7f780af:src/wacc/ast/UnOpNode.java
import wacc.ast.type.TypeNode;
import wacc.symbolTable.TypeEnum;
=======
import wacc.ast.ASTNode;
import wacc.ast.ExprNode;
import wacc.ast.type.TypeEnum;
>>>>>>> Slight refactor:src/wacc/ast/operator/UnOpNode.java

public class UnOpNode extends ExprNode {
  
  private UnaryOperator op;
  private ExprNode expr;

  public UnOpNode(UnaryOperator op, ExprNode expr) {
    super();
    this.op = op;
    this.expr = expr;
    expr.setParent(this);
  }

  @Override
  public TypeNode type() {
    switch (op) {
      case NOT: return new TypeNode(TypeEnum.BOOL);
      case NEG: return new TypeNode(TypeEnum.INT);
      case LEN: return new TypeNode(TypeEnum.INT);
      case ORD: return new TypeNode(TypeEnum.INT);
      case CHR: return new TypeNode(TypeEnum.CHAR);
      default:
        return null;
    }
  }

  @Override
  public boolean isSemanticallyValid() {
    boolean valid = expr.isSemanticallyValid();

    switch (op) {
      case NOT: return expr.type().equals(
          new TypeNode(TypeEnum.BOOL));
      case NEG: return expr.type().equals(
          new TypeNode(TypeEnum.INT));
      case LEN: return expr.type().equals(
          new TypeNode(TypeEnum.ARR));
      case ORD: return expr.type().equals(
          new TypeNode(TypeEnum.CHAR));
      case CHR: return expr.type().equals(
          new TypeNode(TypeEnum.INT));
      default:
        System.err.println("Invalid Unary Operator");
    }

    return valid;
  }

  public void setExpr(ExprNode expr) {
    this.expr = expr;
  }
}
