package wacc.ast;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayElemNodeTest {

  private ArrayElemNode a;
  private IdentNode i1 = new IdentNode("x");
  private IdentNode i2 = new IdentNode("1hgklsjkf"); //invalid
  private ExprNode e1 = new BoolNode(true); // invalid
  private ExprNode e2 = new IntNode(5);

  @Test
  public void arrayElemNodeInit() {
    a = new ArrayElemNode(i1, e2);
    assertTrue(a.getId().equals(i1));
    assertTrue(a.getExpr().equals(e2));
  }

  @Test
  public void arrayElemValidityT() {
    a = new ArrayElemNode(i1, e2);
    assertTrue(i1.isSemanticallyValid());
    assertTrue(e2.isSemanticallyValid());
    assertTrue(a.isSemanticallyValid());
  }

  @Test
  public void arrayElemValidityF1() {
    a = new ArrayElemNode(i1, e1);
    assertTrue(i1.isSemanticallyValid());
    assertTrue(!e1.isSemanticallyValid());
    assertTrue(!a.isSemanticallyValid());
  }

  @Test
  public void arrayElemValidityF2() {
    a = new ArrayElemNode(i2, e2);
    assertTrue(!i2.isSemanticallyValid());
    assertTrue(e2.isSemanticallyValid());
    assertTrue(!a.isSemanticallyValid());
  }

  @Test
  public void arrayElemValidityF3() {
    a = new ArrayElemNode(i2, e1);
    assertTrue(!i2.isSemanticallyValid());
    assertTrue(!e1.isSemanticallyValid());
    assertTrue(!a.isSemanticallyValid());
  }

}
