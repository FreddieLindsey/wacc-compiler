package wacc.ast;

import wacc.symbolTable.TypeEnum;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoolNodeTest {

  private ASTNode parent;
  private BoolNode b;

  @Test
  public void boolNodeInit() {
      b = new BoolNode(parent, true);
      assertTrue(b.getValue());
  }

  @Test
  public void boolNodeValidity() {
      b = new BoolNode(parent, false);
      assert (b.isSemanticallyValid());
  }

  @Test
  public void boolNodeType() {
      b = new BoolNode(parent, false);
      assert (b.type() == TypeEnum.BOOL);
  }

}
