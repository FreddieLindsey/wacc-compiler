package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class BinOpNode extends ExprNode {
	
  private ExprNode lhs;
  private BinaryOperator op;
  private ExprNode rhs;

  public BinOpNode(ASTNode parent, ExprNode lhs, BinaryOperator op, ExprNode rhs) {
    super(parent);
    this.lhs = lhs;
    this.op  = op;
    this.rhs = rhs;
  }

  @Override
  public TypeEnum type() {
    switch(op) {
      case MUL:
      case DIV:
      case MOD:
      case ADD:
      case SUB: return TypeEnum.INT;
      case GT :
      case GTE:
      case LT :
      case LTE:
      case EQ :
      case NEQ:
      case AND:
      case OR : return TypeEnum.BOOL;
    }
    return null;
  }

  @Override
  public boolean isSemanticallyValid() {
    boolean valid = lhs.isSemanticallyValid()
          && rhs.isSemanticallyValid()
          && lhs.type() == rhs.type();

    switch(op) {
      case MUL:
      case DIV:
      case MOD:
      case ADD:
      case SUB: valid &= lhs.type() == TypeEnum.INT; return valid;
      case GT :
      case GTE:
      case LT :
      case LTE: valid &= lhs.type() == TypeEnum.INT
                      || lhs.type() == TypeEnum.CHAR; return valid;
      case EQ :
      case NEQ: valid &= lhs.type() != TypeEnum.STRING; return valid;
      case AND:
      case OR : valid &= lhs.type() == TypeEnum.BOOL; return valid;
      default : return false;
    }
  }

  public ExprNode getLHS() {
    return lhs;
  }

  public ExprNode getRHS() {
    return rhs;
  }
}
