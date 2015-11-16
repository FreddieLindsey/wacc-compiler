package wacc.ast.operator;

import wacc.ast.type.TypeNode;
import wacc.ast.ExprNode;
import wacc.ast.type.TypeEnum;

public class BinOpNode extends ExprNode {

  private ExprNode lhs;
  private BinaryOperator op;
  private ExprNode rhs;

  public BinOpNode(BinaryOperator op) {
    super();
    this.op  = op;
  }

  public void addLHS(ExprNode e) {
    this.lhs = e;
    e.setParent(this);
  }

  public void addRHS(ExprNode e) {
    this.rhs = e;
    e.setParent(this);
  }

  @Override
  public TypeNode type() {
    switch(op) {
      case MUL:
      case DIV:
      case MOD:
      case ADD:
      case SUB: return new TypeNode(TypeEnum.INT);
      case GT :
      case GTE:
      case LT :
      case LTE:
      case EQ :
      case NEQ:
      case AND:
      case OR : return new TypeNode(TypeEnum.BOOL);
    }
    return null;
  }

  @Override
  public boolean isSemanticallyValid() {
    boolean valid =
           lhs != null && rhs != null
        && lhs.isSemanticallyValid()
        && rhs.isSemanticallyValid()
        && lhs.type() == rhs.type();

    if (!valid) return false;

    switch(op) {
      case MUL:
      case DIV:
      case MOD:
      case ADD:
      case SUB: return lhs.type().equals(new TypeNode(TypeEnum.INT));
      case GT :
      case GTE:
      case LT :
      case LTE: return lhs.type().equals(new TypeNode(TypeEnum.INT))
                    || lhs.type().equals(new TypeNode(TypeEnum.CHAR));
      case EQ :
      case NEQ: return !lhs.type().equals(new TypeNode(TypeEnum.STRING));
      case AND:
      case OR : return lhs.type().equals(new TypeNode(TypeEnum.BOOL));
      default : return false;
    }
  }

  public ExprNode getLHS() {
    return lhs;
  }

  public ExprNode getRHS() {
    return rhs;
  }

  public void setLHS(ExprNode LHS) {
    this.lhs = LHS;
  }

  public void setRHS(ExprNode RHS) {
    this.rhs = RHS;
  }
}
