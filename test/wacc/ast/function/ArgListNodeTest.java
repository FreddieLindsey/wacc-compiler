package wacc.ast.function;

import org.junit.Test;
import wacc.ast.ExprNode;
import wacc.ast.type.IntNode;

import static org.junit.Assert.assertTrue;

public class ArgListNodeTest {

  private ArgListNode a;
  private ExprNode e1 = new IntNode(5);
  private ExprNode e2 = new IntNode(10);

  @Test
  public void pairElemInit() {
    a = new ArgListNode();
    a.addExpr(e1);
    a.addExpr(e2);
    assertTrue(a.getExprs().get(0).equals(e1));
    assertTrue(a.getExprs().get(1).equals(e2));
  }

  @Test
  public void pairElemValidityT() {
    a = new ArgListNode();
    a.addExpr(e1);
    a.addExpr(e2);
    assertTrue(e1.isSemanticallyValid());
    assertTrue(e2.isSemanticallyValid());
    assertTrue(a.isSemanticallyValid());
  }

}
