package wacc.ast;

import org.junit.Test;
import wacc.symbolTable.TypeEnum;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ParamListNodeTest {

  private ASTNode parent;
  private ParamListNode p;
  private ParamNode[] ps = new ParamNode[2];

  private TypeEnum t1 = TypeEnum.BOOL;
  private ParamNode p1 = new ParamNode(p, t1, new IdentNode(null, "a"));
  private ParamNode p2 = new ParamNode(p, t1, new IdentNode(null, "b"));
  private ParamNode p3 = new ParamNode(p, t1, new IdentNode(null, "@lkjsdkg"));

  @Test
  public void canGetParams() {
    ps[0] = p1;
    ps[1] = p2;
    p = new ParamListNode(parent, ps);
    assertTrue(Arrays.equals(p.getParams(), ps));
  }

  @Test
  public void validWithValidParams() {
    ps[0] = p1;
    ps[1] = p2;
    p = new ParamListNode(parent, ps);
    assertTrue(p.isSemanticallyValid());
  }

  @Test
  public void invalidWithInvalidParams() {
    ps[0] = p1;
    ps[1] = p3;
    p = new ParamListNode(parent, ps);
    assertFalse(p.isSemanticallyValid());
  }

  private void setParents() {
    p1.getIdent().setParent(p1);
    p2.getIdent().setParent(p2);
    p3.getIdent().setParent(p3);
  }

}
