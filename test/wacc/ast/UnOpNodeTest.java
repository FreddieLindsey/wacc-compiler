package wacc.ast;

import org.junit.Test;
import wacc.symbolTable.TypeEnum;

public class UnOpNodeTest {

  private UnOpNode u;

  @Test
  public void canCreateUnOpNode() {
    u = new UnOpNode(UnaryOperator.NOT, new ExprNode() {
      @Override
      public TypeEnum type() {
        return null;
      }

      @Override
      public boolean isSemanticallyValid() {
        return true;
      }
    });
  }

}
