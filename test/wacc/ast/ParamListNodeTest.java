package wacc.ast;

import org.junit.Test;
import wacc.symbolTable.TypeEnum;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ParamListNodeTest {

  private ParamListNode p;
  private ParamNode[] ps = new ParamNode[2];

  private TypeEnum t1 = TypeEnum.BOOL;
  private ParamNode p1 = new ParamNode(t1, new IdentNode("a"));
  private ParamNode p2 = new ParamNode(t1, new IdentNode("b"));
  private ParamNode p3 = new ParamNode(t1, new IdentNode("@lkjsdkg"));

  @Test
  public void canGetParams() {
    ps[0] = p1;
    ps[1] = p2;
    p = new ParamListNode(ps);
    assertTrue(Arrays.equals(p.getParams(), ps));
  }

  @Test
  public void validWithValidParams() {
    ps[0] = p1;
    ps[1] = p2;
    p = new ParamListNode(ps);
    assertTrue(p.isSemanticallyValid());
  }

  @Test
  public void invalidWithInvalidParams() {
    ps[0] = p1;
    ps[1] = p3;
    p = new ParamListNode(ps);
    assertFalse(p.isSemanticallyValid());
  }


}
