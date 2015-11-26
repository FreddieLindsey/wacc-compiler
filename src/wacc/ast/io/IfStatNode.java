package wacc.ast.io;

import wacc.ast.ExprNode;
import wacc.ast.type.TypeEnum;
import wacc.ast.type.TypeNode;
import wacc.backend.*;

import java.util.ArrayList;

public class IfStatNode extends StatNode {

  private ExprNode expr;
  private StatNode stat1;
  private StatNode stat2;

  public IfStatNode(ExprNode expr, StatNode stat1, StatNode stat2) {
    super();
    this.expr = expr;
    this.stat1 = stat1;
    this.stat2 = stat2;
    expr.setParent(this);
    stat1.setParent(this);
    stat2.setParent(this);
  }

  public ExprNode getExpr() {
    return this.expr;
  }

  public StatNode getTrueBranch() {
    return this.stat1;
  }

  public StatNode getFalseBranch() {
    return this.stat2;
  }

  @Override
  public boolean isSemanticallyValid() {
    semanticallyValid = expr.isSemanticallyValid()
      && stat1.isSemanticallyValid()
      && stat2.isSemanticallyValid()
      && expr.type().getType().equals(TypeEnum.BOOL);
    return semanticallyValid;
  }

  @Override
  public TypeNode returnType() {
    TypeNode true_ = stat1.returnType();
    TypeNode false_ = stat2.returnType();
    return (true_ != null) ? true_ : false_;
  }

  @Override
  public boolean returns() {
    return stat1.returns() && stat2.returns();
  }

  @Override
  public ArrayList<AssemblyInstr> generateCode() {
    ArrayList<AssemblyInstr> instrs = new ArrayList<AssemblyInstr>();
    return instrs;
  }

}
