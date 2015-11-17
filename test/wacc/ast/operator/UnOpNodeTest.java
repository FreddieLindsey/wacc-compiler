package wacc.ast.operator;

import org.junit.Test;
import wacc.ast.ASTNode;
import wacc.ast.ExprNode;
import wacc.ast.operator.UnOpNode;
import wacc.ast.operator.UnaryOperator;
import wacc.ast.type.TypeNode;

public class UnOpNodeTest {

  private UnOpNode u;

  @Test
  public void canCreateUnOpNode() {
    u = new UnOpNode(UnaryOperator.NOT);
  }

}
