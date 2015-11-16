package wacc.ast;

import org.junit.Test;
import wacc.symbolTable.TypeEnum;

public class UnOpNodeTest {

  private ASTNode parent;
  private UnOpNode u;

  @Test
  public void canCreateUnOpNode() {
    u = new UnOpNode(parent, UnaryOperator.NOT, new ExprNode(u) {
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
