package wacc.ast;

import org.junit.Test;

import static org.junit.Assert.*;

public class IfStatNodeTest {

  @Test
  public void removethisFunction() {
    assert(true);
  }

  private IfStatNode i;

  private ExprNode e1 = new BoolNode(true);
  private ExprNode e2 = new StringNode("Hello World!"); //not valid in this context
  private StatNode s1 = new BasicStatNode(StatTypeEnum.SKIP, null);
  private StatNode s2 = new BasicStatNode(StatTypeEnum.RETURN, null); //invalid

  @Test
  public void ifStatNodeInit() {
    i = new IfStatNode(e1, s1, s1);
    assertTrue(i.getExpr().equals(e1));
    assertTrue(i.getTrueBranch().equals(s1));
    assertTrue(i.getFalseBranch().equals(s1));
  }

  @Test
  public void ifStatNodeValidT() {
    i = new IfStatNode(e1, s1, s1);
    assertTrue(e1.isSemanticallyValid());
    assertTrue(s1.isSemanticallyValid());
    assertTrue(i.isSemanticallyValid());
  }

  @Test
  public void ifStatNodeValidF() {
    //e2 is not bool -> invalid
    i = new IfStatNode(e2, s1, s1);
    assertTrue(e2.isSemanticallyValid());
    assertTrue(s1.isSemanticallyValid());
    assertTrue(!i.isSemanticallyValid());
  }

  @Test
  public void ifStatNodeValidF2() {
    i = new IfStatNode(e1, s1, s2);
    assertTrue(e1.isSemanticallyValid());
    assertTrue(s1.isSemanticallyValid());
    assertTrue(!s2.isSemanticallyValid());
    assertTrue(!i.isSemanticallyValid());
  }



}
