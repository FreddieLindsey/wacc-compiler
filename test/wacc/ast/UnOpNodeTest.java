package wacc.ast;

import org.junit.Test;
<<<<<<< 13bee15090abd00d60efafe016aec785e7f780af
=======
import wacc.ast.operator.UnOpNode;
import wacc.ast.operator.UnaryOperator;
>>>>>>> Slight refactor

public class UnOpNodeTest {

  private UnOpNode u;

  @Test
  public void canCreateUnOpNode() {
    u = new UnOpNode(UnaryOperator.NOT, new ExprNode() {
      @Override
      public TypeNode type() {
        return null;
      }

      @Override
      public boolean isSemanticallyValid() {
        return true;
      }
    });
  }

}
