package wacc.ast;

import wacc.ast.type.TypeNode;
import wacc.symbolTable.TypeEnum;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoolNodeTest {

  private BoolNode b;

  @Test
  public void boolNodeInit() {
      b = new BoolNode(true);
      assertTrue(b.getValue());
  }

  @Test
  public void boolNodeValidity() {
      b = new BoolNode(false);
      assert (b.isSemanticallyValid());
  }

  @Test
  public void boolNodeType() {
      b = new BoolNode(false);
      assert (b.type() == new TypeNode(TypeEnum.BOOL));
  }

}
