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

  private CallNode c;
  private IdentNode ident = new IdentNode("x");

  @Test
  public void canGetIdent() {
    c = new CallNode(ident, new ArgListNode());
    assertTrue(c.getIdent().equals(ident));
  }

}
