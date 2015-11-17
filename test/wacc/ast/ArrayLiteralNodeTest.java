package wacc.ast;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ArrayLiteralNodeTest {

  private ArrayLiteralNode a;
  private ExprNode e1 = new IntNode(1);
  private ExprNode e2 = new StringNode("bob's your uncle");

  @Test
  public void canGetArrayFromNode() {
    a = new ArrayLiteralNode();
    a.addExpr(e1);
    a.addExpr(e2);
    assertTrue(a.getExprs().get(0).equals(e1));
    assertTrue(a.getExprs().get(1).equals(e2));
  }

  @Test
  public void validWhenValid() {
    a = new ArrayLiteralNode();
    a.addExpr(e1);
    assertTrue(a.isSemanticallyValid());
  }

  @Test
  public void notValidWhenNotValid() {
    a = new ArrayLiteralNode();
    a.addExpr(e2);
    assertFalse(a.isSemanticallyValid());
  }

  @Test
  public void notValidWhenNotValidNotWholeArray() {
    a = new ArrayLiteralNode();
    a.addExpr(e1);
    a.addExpr(e2);
    assertFalse(a.isSemanticallyValid());
  }
}
