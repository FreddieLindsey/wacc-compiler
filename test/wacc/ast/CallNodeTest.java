package wacc.ast;

import org.junit.Test;

import static org.junit.Assert.*;

public class CallNodeTest {

  private CallNode c;
  private IdentNode ident = new IdentNode("x");
  private ExprNode e1 = new IntNode(5);
  private ExprNode e2 = new IntNode(10);
  private ExprNode e3 = new IntNode(Integer.MAX_VALUE + 1000L); //invalid
  private ArgListNode argsV = new ArgListNode(new ExprNode[]{e1, e2});
  private ArgListNode argsINV = new ArgListNode(new ExprNode[]{e1, e2, e3}); //invalid


  @Test
  public void callInit() {
    c = new CallNode(ident, argsV);

    assertTrue(c.getIdent().equals(ident));
    assertTrue(c.getArgs().equals(argsV));
  }

  @Test
  public void callValidityT() {
    c = new CallNode(ident, argsV);

    assertTrue(ident.isSemanticallyValid());
    assertTrue(argsV.isSemanticallyValid());
    assertTrue(c.isSemanticallyValid());
  }

  @Test
  public void callValidityF() {
    c = new CallNode(ident, argsINV);

    assertTrue(ident.isSemanticallyValid());
    assertTrue(!argsINV.isSemanticallyValid());
    assertTrue(!c.isSemanticallyValid());
  }

}
