package wacc.ast;

import wacc.symbolTable.TypeEnum;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntNodeTest {

  private IntNode i;

  @Test
  public void intNodeInit() {
    i = new IntNode(100);
    assertTrue(i.getValue() == 100);
  }

  @Test
  public void intNodeValidity() {
    i = new IntNode(Integer.MAX_VALUE);
    assertTrue(i.isSemanticallyValid());
  }

  @Test
  public void intNodeNotValid() {
    i = new IntNode(Integer.MAX_VALUE * 2);
    assertTrue(!i.isSemanticallyValid());
  }

  @Test
  public void intNodeType() {
    i = new IntNode(Integer.MAX_VALUE);
    assertTrue(i.type() == TypeEnum.INT);
  }
}
