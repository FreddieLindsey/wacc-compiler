package wacc.ast;

import wacc.symbolTable.TypeEnum;
import org.junit.Test;

public class BoolNodeTest {

  private BoolNode b;

  @Test
  public void boolNodeInit() {
      b = new BoolNode(true);
      assert(b.getValue());
  }

  @Test
  public void boolNodeValidity() {
      b = new BoolNode(false);
      assert (b.isSemanticallyValid());
  }

  @Test
  public void boolNodeType() {
      b = new BoolNode(false);
      assert (b.type() == TypeEnum.BOOL);
  }

}