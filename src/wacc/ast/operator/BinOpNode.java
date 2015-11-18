package wacc.ast.operator;

import wacc.ast.IdentNode;
import wacc.ast.type.TypeNode;
import wacc.ast.ExprNode;
import wacc.ast.AssignNode;
import wacc.ast.type.TypeEnum;

public class BinOpNode extends AssignNode {

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
    TypeNode lhs_type, rhs_type;

    // Check null
    if (lhs == null || rhs == null) return false;

    // Check lhs and rhs are valid
    if ( !lhs.isSemanticallyValid()
      || !rhs.isSemanticallyValid()) return false;

    // Check lhs
    lhs_type = (lhs instanceof IdentNode) ?
      symbolTable.lookUp(((IdentNode) lhs).getIdent()) :
      lhs.type();

    // Check rhs
    rhs_type = (rhs instanceof IdentNode) ?
      symbolTable.lookUp(((IdentNode) rhs).getIdent()) :
      rhs.type();

    if (!lhs_type.equals(rhs_type)) return false;

    switch(op) {
      case MUL:
      case DIV:
      case MOD:
      case ADD:
      case SUB: return lhs_type.equals(new TypeNode(TypeEnum.INT));
      case GT :
      case GTE:
      case LT :
      case LTE: return lhs_type.equals(new TypeNode(TypeEnum.INT))
                    || lhs_type.equals(new TypeNode(TypeEnum.CHAR));
      case EQ :
      case NEQ: return !lhs_type.equals(new TypeNode(TypeEnum.STRING));
      case AND:
      case OR : return lhs_type.equals(new TypeNode(TypeEnum.BOOL));
      default : return false;
    }
  }

  public ExprNode getLHS() {
    return lhs;
  }

  public ExprNode getRHS() {
    return rhs;
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
