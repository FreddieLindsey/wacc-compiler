package wacc.ast;

import wacc.symbolTable.TypeEnum;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringNodeTest {

  private StringNode s;

  @Test
  public void stringNodeInit() {
    s = new StringNode("test");
    assertTrue(s.getValue() == "test");
  }

  @Test
  public void stringNodeValidityT() {
    s = new StringNode("test");
    assertTrue(s.isSemanticallyValid());
  }

  @Test
  public void stringNodeValidityF() {
    s = new StringNode("tes\t");
    assertTrue(!s.isSemanticallyValid());
  }

  @Test
  public void stringNodeType() {
    s = new StringNode("test");
    assertTrue(s.type() == TypeEnum.STRING);
  }
}
