package wacc.ast;

import org.junit.Test;

public class UnOpNodeTest {

  private UnOpNode u;

  @Test
  public void canCreateUnOpNode() {
    u = new UnOpNode(UnaryOperator.NOT, new ExprNode() {
      @Override
      public boolean isSemanticallyValid() {
        return true;
      }
    });
  }

}
