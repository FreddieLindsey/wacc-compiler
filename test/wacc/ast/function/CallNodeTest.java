package wacc.ast.function;

import org.junit.Test;
import wacc.ast.ASTNode;
import wacc.ast.ExprNode;
import wacc.ast.IdentNode;
import wacc.ast.function.ArgListNode;
import wacc.ast.function.CallNode;
import wacc.ast.type.IntNode;

import static org.junit.Assert.*;

public class CallNodeTest {

  private ASTNode parent;
  private CallNode c;
  private IdentNode ident = new IdentNode("x");
  private ExprNode e1 = new IntNode(5);
  private ExprNode e2 = new IntNode(10);
  private ExprNode e3 = new IntNode(Integer.MAX_VALUE + 1000L); //invalid

  @Test
  public void callInit() {
    c = new CallNode(ident);
    assertTrue(c.getIdent().equals(ident));
  }

  @Test
  public void callValidityT() {
    c = new CallNode(ident);
    c.addArg(e1);
    c.addArg(e2);
    assertTrue(ident.isSemanticallyValid());
    assertTrue(c.getArgs().isSemanticallyValid());
    assertTrue(c.isSemanticallyValid());
  }

  @Test
  public void callValidityF() {
    c = new CallNode(ident);
    c.addArg(e2);
    c.addArg(e3);
    assertTrue(ident.isSemanticallyValid());
    assertFalse(c.getArgs().isSemanticallyValid());
    assertFalse(c.isSemanticallyValid());
  }

}
