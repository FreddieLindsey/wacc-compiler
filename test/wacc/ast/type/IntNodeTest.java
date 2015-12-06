package wacc.ast.type;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

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
    i = new IntNode(Integer.MAX_VALUE * 2L);
    assertTrue(!i.isSemanticallyValid());
  }

  @Test
  public void intNodeType() {
    i = new IntNode(Integer.MAX_VALUE);
    assertTrue(i.type().equals(new TypeNode(TypeEnum.INT)));
  }

}
