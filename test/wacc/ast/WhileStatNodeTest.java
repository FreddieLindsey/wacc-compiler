package wacc.ast;

import org.junit.Test;

import static org.junit.Assert.*;

public class WhileStatNodeTest {

  private WhileStatNode w;

  private ExprNode e1 = new BoolNode(true);
  private ExprNode e2 = new StringNode("bob"); // invalid for while loop Expr
  private StatNode s1 = new BasicStatNode(StatTypeEnum.SKIP, null);
  private StatNode s2 = new BasicStatNode(StatTypeEnum.RETURN, null); // invalid

  @Test
  public void canGetExpr() {
    w = new WhileStatNode(e1, s1);
    assertTrue(w.getExpr().equals(e1));
  }

  @Test
  public void canGetStat() {
    w = new WhileStatNode(e1, s1);
    assertTrue(w.getStat().equals(s1));
  }

  @Test
  public void whileStatNodeValidT() {
    w = new WhileStatNode(e1, s1);
    assertTrue(e1.isSemanticallyValid());
    assertTrue(s1.isSemanticallyValid());
    assertTrue(w.isSemanticallyValid());
  }

  @Test
  public void whileStatNodeValidF() {
    w = new WhileStatNode(e1, s2);
    assertTrue(e1.isSemanticallyValid());
    assertFalse(s2.isSemanticallyValid());
    assertFalse(w.isSemanticallyValid());
  }

  @Test
  public void whileStatNodeValidF2() {
    w = new WhileStatNode(e2, s1);
    assertTrue(e2.isSemanticallyValid());
    assertTrue(s1.isSemanticallyValid());
    assertFalse(w.isSemanticallyValid());
  }

}
