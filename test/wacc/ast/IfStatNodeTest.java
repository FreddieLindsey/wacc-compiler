package wacc.ast;

import org.junit.Test;

import static org.junit.Assert.*;

public class IfStatNodeTest {

  private ASTNode parent;
  private IfStatNode i;

  private ExprNode e1 = new BoolNode(i, true);
  private ExprNode e2 = new StringNode(i, "Hello World!"); //not valid in this context
  private StatNode s1 = new BasicStatNode(i, StatTypeEnum.SKIP, null);
  private StatNode s2 = new BasicStatNode(i, StatTypeEnum.RETURN, null); //invalid

  @Test
  public void canGetExpr() {
    i = new IfStatNode(parent, e1, s1, s2);
    assertTrue(i.getExpr().equals(e1));
  }

    @Test
  public void canGetTrueBranch() {
    i = new IfStatNode(parent, e1, s1, s2);
    assertTrue(i.getTrueBranch().equals(s1));
  }

    @Test
  public void canGetFalseBranch() {
    i = new IfStatNode(parent, e1, s1, s2);
    assertTrue(i.getFalseBranch().equals(s2));
  }

  @Test
  public void ifStatNodeValidT() {
    i = new IfStatNode(parent, e1, s1, s1);
    assertTrue(e1.isSemanticallyValid());
    assertTrue(s1.isSemanticallyValid());
    assertTrue(i.isSemanticallyValid());
  }

  @Test
  public void ifStatNodeValidF() {
    i = new IfStatNode(parent, e2, s1, s1);
    assertTrue(e2.isSemanticallyValid());
    assertTrue(s1.isSemanticallyValid());
    assertTrue(!i.isSemanticallyValid());
  }

  @Test
  public void ifStatNodeValidF2() {
    i = new IfStatNode(parent, e1, s1, s2);
    assertTrue(e1.isSemanticallyValid());
    assertTrue(s1.isSemanticallyValid());
    assertTrue(!s2.isSemanticallyValid());
    assertTrue(!i.isSemanticallyValid());
  }



}
