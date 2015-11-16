package wacc.ast;

import org.junit.Test;

import static org.junit.Assert.*;

public class PairNodeTest {

  private ASTNode parent;
  private PairNode<ExprNode, ExprNode> n;
  private ExprNode e1 = new IntNode(n, 5);
  private ExprNode e2 = new IntNode(n, 10);
  private ExprNode e3 = new IntNode(n, Integer.MAX_VALUE + 1000L);

  @Test
  public void canGetFst() {
    n = new PairNode<>(parent, e1, e2);
    assertTrue(n.getFst().equals(e1));
  }

  @Test
  public void canGetSnd() {
    n = new PairNode<>(parent, e1, e2);
    assertTrue(n.getSnd().equals(e2));
  }

  @Test
  public void pairElemValidityT() {
    n = new PairNode<>(parent, e1, e2);
    assertTrue(e1.isSemanticallyValid());
    assertTrue(e2.isSemanticallyValid());
    assertTrue(n.isSemanticallyValid());
  }

  @Test
  public void pairElemValidityF() {
    n = new PairNode<>(parent, e1, e3);
    assertTrue(e1.isSemanticallyValid());
    assertFalse(e3.isSemanticallyValid());
    assertFalse(n.isSemanticallyValid());
  }

}
