package wacc.ast;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ArrayLiteralNodeTest {

  private ASTNode parent;
  private ArrayLiteralNode a;
  private ExprNode[] es = new ExprNode[2];
  private ExprNode e1 = new IntNode(1);
  private ExprNode e2 = new StringNode("bob's your uncle");

  @Test
  public void canGetArrayFromNode() {
    a = new ArrayLiteralNode(es);
    assertTrue(Arrays.equals(a.getValue(), es));
  }

  @Test
  public void validWhenValid() {
    es[0] = e1;
    es[1] = e1;
    a = new ArrayLiteralNode(es);
    assertTrue(a.isSemanticallyValid());
  }

  @Test
  public void notValidWhenNotValid() {
    es[0] = e2;
    es[1] = e2;
    a = new ArrayLiteralNode(es);
    assertFalse(a.isSemanticallyValid());
  }

  @Test
  public void notValidWhenNotValidNotWholeArray() {
    es[0] = e1;
    es[1] = e2;
    a = new ArrayLiteralNode(es);
    assertFalse(a.isSemanticallyValid());
  }
}
