package wacc.ast;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayElemNodeTest {

  private ASTNode parent;
  private ArrayElemNode a;
  private IdentNode i1 = new IdentNode(a, "x");
  private IdentNode i2 = new IdentNode(a, "1hgklsjkf"); //invalid
  private ExprNode e1 = new BoolNode(a, true); // invalid
  private ExprNode e2 = new IntNode(a, 5);

  @Test
  public void arrayElemNodeInit() {
    a = new ArrayElemNode(parent, i1, e2);
    assertTrue(a.getId().equals(i1));
    assertTrue(a.getExpr().equals(e2));
  }

  @Test
  public void arrayElemValidityT() {
    a = new ArrayElemNode(parent, i1, e2);
    assertTrue(i1.isSemanticallyValid());
    assertTrue(e2.isSemanticallyValid());
    assertTrue(a.isSemanticallyValid());
  }

  @Test
  public void arrayElemValidityF1() {
    a = new ArrayElemNode(parent, i1, e1);
    assertTrue(i1.isSemanticallyValid());
    assertTrue(!e1.isSemanticallyValid());
    assertTrue(!a.isSemanticallyValid());
  }

  @Test
  public void arrayElemValidityF2() {
    a = new ArrayElemNode(parent, i2, e2);
    assertTrue(!i2.isSemanticallyValid());
    assertTrue(e2.isSemanticallyValid());
    assertTrue(!a.isSemanticallyValid());
  }

  @Test
  public void arrayElemValidityF3() {
    a = new ArrayElemNode(parent, i2, e1);
    assertTrue(!i2.isSemanticallyValid());
    assertTrue(!e1.isSemanticallyValid());
    assertTrue(!a.isSemanticallyValid());
  }

}
