package wacc.ast.io;

import org.junit.Test;
import wacc.ast.ASTNode;
import wacc.ast.ExprNode;
import wacc.ast.StatNode;
import wacc.ast.StatTypeEnum;
import wacc.ast.io.BasicStatNode;
import wacc.ast.io.IfStatNode;
import wacc.ast.type.BoolNode;
import wacc.ast.type.StringNode;

import static org.junit.Assert.*;

public class IfStatNodeTest {

  private IfStatNode i;

  private ExprNode e1 = new BoolNode(true);
  private ExprNode e2 = new StringNode("Hello World!"); //not valid in this context
  private StatNode s1 = new BasicStatNode(StatTypeEnum.SKIP);
  private StatNode s2 = new BasicStatNode(StatTypeEnum.RETURN); //invalid

  @Test
  public void canGetExpr() {
    i = new IfStatNode(e1, s1, s2);
    assertTrue(i.getExpr().equals(e1));
  }

    @Test
  public void canGetTrueBranch() {
    i = new IfStatNode(e1, s1, s2);
    assertTrue(i.getTrueBranch().equals(s1));
  }

    @Test
  public void canGetFalseBranch() {
    i = new IfStatNode(e1, s1, s2);
    assertTrue(i.getFalseBranch().equals(s2));
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
