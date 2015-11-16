package wacc.ast;

import wacc.symbolTable.TypeEnum;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringNodeTest {

  private ASTNode parent;
  private StringNode s;

  @Test
  public void stringNodeInit() {
    s = new StringNode(parent, "test");
    assertTrue(s.getValue().equals("test"));
  }

  @Test
  public void stringNodeValidityT() {
    s = new StringNode(parent, "test");
    assertTrue(s.isSemanticallyValid());
  }

  @Test
  public void stringNodeValidityF() {
    s = new StringNode(parent, "tes\\t");
    assertFalse(s.isSemanticallyValid());
  }

  @Test
  public void stringNodeType() {
    s = new StringNode(parent, "test");
    assertTrue(s.type() == TypeEnum.STRING);
  }
}
