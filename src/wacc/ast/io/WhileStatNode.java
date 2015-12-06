package wacc.ast.io;

import wacc.ast.ExprNode;
import wacc.ast.IdentNode;
import wacc.ast.type.TypeEnum;
import wacc.ast.type.TypeNode;
import wacc.backend.instruction.Instruction;

import java.util.ArrayList;

public class WhileStatNode extends StatNode {

  private ExprNode expr;
  private StatNode stat;

  public WhileStatNode(ExprNode expr, StatNode stat) {
    super();
    this.expr = expr;
    this.stat = stat;
    expr.setParent(this);
    stat.setParent(this);
  }

  public ExprNode getExpr() {
    return this.expr;
  }

  public StatNode getStat() {
    return this.stat;
  }

  @Override
  public boolean isSemanticallyValid() {
    if (expr instanceof IdentNode &&
      symbolTable.lookUp(((IdentNode) expr).getIdent()) == null) return false;

    semanticallyValid = stat.isSemanticallyValid()
      && expr.isSemanticallyValid()
      && expr.type().getType().equals(TypeEnum.BOOL);
    return semanticallyValid;
  }

  @Override
  public TypeNode returnType() {
    return stat.returnType();
  }

  @Override
  public ArrayList<Instruction> generateCode() {
    ArrayList<Instruction> instrs = new ArrayList<Instruction>();
    return instrs;
  }

}
