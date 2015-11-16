package wacc.ast;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArgListNodeTest {

  private ArgListNode a;
  private ExprNode e1 = new IntNode(5);
  private ExprNode e2 = new IntNode(10);
  private ExprNode e3 = new IntNode(Integer.MAX_VALUE + 1000L);

  @Test
  public void pairElemInit() {
    a = new ArgListNode(new ExprNode[]{e1, e2});
    assertTrue(a.getExprs()[0].equals(e1));
    assertTrue(a.getExprs()[1].equals(e2));
  }

  @Test
  public void pairElemValidityT() {
    a = new ArgListNode(new ExprNode[]{e1, e2});
    assertTrue(e1.isSemanticallyValid());
    assertTrue(e2.isSemanticallyValid());
    assertTrue(a.isSemanticallyValid());
  }

  @Test
  public void pairElemValidityF() {
    a = new ArgListNode(new ExprNode[]{e1, e3});
    assertTrue(e1.isSemanticallyValid());
    assertTrue(!e3.isSemanticallyValid());
    assertTrue(!a.isSemanticallyValid());
  }

}