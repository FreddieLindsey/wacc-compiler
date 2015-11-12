package wacc.ast;

public class BinOpNode implements ExprNode {
	
  private ExprNode lhs;
  private BinaryOperator op;
  private ExprNode rhs;

  public BinOpNode(ExprNode lhs, BinaryOperator op, ExprNode rhs) {

    this.lhs = lhs;
    this.op  = op;
    this.rhs = rhs;

  }

  @Override
  public boolean isSemanticallyValid() {
    //TODO: check lhs and rhs collpase down to same type
    //TODO: check that these matching types can have the operator applied to them
    boolean valid = true;

    valid &= lhs.isSemanticallyValid()
          && rhs.isSemanticallyValid();

    valid &= lhs.type() = rhs.type();

    switch(op) {
      case MUL:
      case DIV:
      case MOD:
      case ADD:
      case SUB: valid &= lhs.type() == TypeEnum.INT; break;
      case GT :
      case GTE:
      case LT :
      case LTE: valid &= (lhs.type() == TypeEnum.INT
                        ||lhs.type() == TypeEnum.CHAR; break; 
      case EQ :
      case NEQ: valid &= (lhs.type() != TypeEnum.STRING; break;
      case AND:
      case OR : valid &= (lhs.type() == TypeEnum.BOOL;
    }

    return semValid;
  }

}
