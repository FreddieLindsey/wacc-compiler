package wacc.ast;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class ArrayElemNodeTest {

  private ArrayElemNode a;
  private IdentNode i1 = new IdentNode("x");
  private IdentNode i2 = new IdentNode("1hgklsjkf"); //invalid
  private ExprNode e1 = new BoolNode(true); // invalid
  private ExprNode e2 = new IntNode(5);
  private ExprNode e3 = new IntNode(10);
  private ExprNode e4 = new IntNode(15);

  @Test
  public void canGetIdent() {
    a = new ArrayElemNode(i1);
    a.addExpr(e2);
    assertTrue(a.getId().equals(i1));
  }

  @Test
  public void canGetExpr() {
    a = new ArrayElemNode(i1);
    a.addExpr(e2);
    assertTrue(a.getExprs().get(0).equals(e2));
  }

  @Test
  public void arrayElemValidityT() {
    a = new ArrayElemNode(i1);
    a.addExpr(e2);
    assertTrue(i1.isSemanticallyValid());
    assertTrue(e2.isSemanticallyValid());
    assertTrue(a.isSemanticallyValid());
  }

  @Test
  public void arrayElemValidityF1() {
    a = new ArrayElemNode(i1);
    a.addExpr(e1);
    assertTrue(i1.isSemanticallyValid());
    assertFalse(a.isSemanticallyValid());
  }

  @Test
  public void arrayElemValidityF2() {
    a = new ArrayElemNode(i2);
    a.addExpr(e2);
    assertTrue(!i2.isSemanticallyValid());
    assertTrue(e2.isSemanticallyValid());
    assertTrue(!a.isSemanticallyValid());
  }

  @Test
  public void arrayElemValidityF3() {
    a = new ArrayElemNode(i2);
    a.addExpr(e1);
    assertTrue(!i2.isSemanticallyValid());
    assertTrue(!a.isSemanticallyValid());
  }

  @Test
  public void arrayElemMultipleExpr() {
    a = new ArrayElemNode(i1);
    a.addExpr(e2);
    a.addExpr(e3);
    a.addExpr(e4);
    ArrayList<ExprNode> exprs = a.getExprs();
    assertTrue(exprs.get(0).equals(e2));
    assertTrue(exprs.get(1).equals(e3));
    assertTrue(exprs.get(2).equals(e4));
  }

}
