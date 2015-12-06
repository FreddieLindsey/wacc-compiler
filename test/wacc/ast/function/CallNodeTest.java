package wacc.ast.function;

import org.junit.Test;
import wacc.ast.IdentNode;

import static org.junit.Assert.assertTrue;

public class CallNodeTest {

  private CallNode c;
  private IdentNode ident = new IdentNode("x");

  @Test
  public void canGetIdent() {
    c = new CallNode(ident, new ArgListNode());
    assertTrue(c.getIdent().equals(ident));
  }

}
