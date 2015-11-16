package wacc.ast;

import wacc.symbolTable.TypeEnum;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntNodeTest {

  private ASTNode parent;
  private IntNode i;

  @Test
  public void intNodeInit() {
    i = new IntNode(parent, 100);
    assertTrue(i.getValue() == 100);
  }

  @Test
  public void intNodeValidity() {
    i = new IntNode(parent, Integer.MAX_VALUE);
    assertTrue(i.isSemanticallyValid());
  }

  @Test
  public void intNodeNotValid() {
    i = new IntNode(parent, Integer.MAX_VALUE * 2L);
    assertTrue(!i.isSemanticallyValid());
  }

  @Test
  public void intNodeType() {
    i = new IntNode(parent, Integer.MAX_VALUE);
    assertTrue(i.type() == TypeEnum.INT);
  }
}
