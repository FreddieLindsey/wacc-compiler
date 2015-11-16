package wacc.ast;

import org.junit.Test;

import static org.junit.Assert.*;

public class CallNodeTest {

  private ASTNode parent;
  private CallNode c;
  private IdentNode ident = new IdentNode(c, "x");
  private ExprNode e1 = new IntNode(null, 5);
  private ExprNode e2 = new IntNode(null, 10);
  private ExprNode e3 = new IntNode(null, Integer.MAX_VALUE + 1000L); //invalid
  private ArgListNode argsV = new ArgListNode(c, new ExprNode[]{e1, e2});
  private ArgListNode argsINV = new ArgListNode(c, new ExprNode[]{e1, e2, e3}); //invalid

  @Test
  public void callInit() {
    c = new CallNode(parent, ident, argsV);
    setParentForArray(argsV);
    assertTrue(c.getIdent().equals(ident));
    assertTrue(c.getArgs().equals(argsV));
  }

  @Test
  public void callValidityT() {
    c = new CallNode(parent, ident, argsV);
    setParentForArray(argsV);
    assertTrue(ident.isSemanticallyValid());
    assertTrue(argsV.isSemanticallyValid());
    assertTrue(c.isSemanticallyValid());
  }

  @Test
  public void callValidityF() {
    c = new CallNode(parent, ident, argsINV);
    setParentForArray(argsINV);
    assertTrue(ident.isSemanticallyValid());
    assertTrue(!argsINV.isSemanticallyValid());
    assertTrue(!c.isSemanticallyValid());
  }

  private void setParentForArray(ASTNode p) {
    e1.setParent(p);
    e2.setParent(p);
    e3.setParent(p);
  }

}
