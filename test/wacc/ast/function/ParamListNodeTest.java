package wacc.ast.function;

import org.junit.Test;
import wacc.ast.type.TypeEnum;
import wacc.ast.IdentNode;
import static org.junit.Assert.*;

public class ParamListNodeTest {

  private ParamListNode p;
  private TypeEnum t1 = TypeEnum.BOOL;
  private TypeEnum t2 = TypeEnum.INT;
  private ParamNode p1 = new ParamNode(t1, new IdentNode("a"));
  private ParamNode p2 = new ParamNode(t1, new IdentNode("b"));
  private ParamNode p3 = new ParamNode(t1, new IdentNode("@lkjsdkg"));
  private ParamNode p4 = new ParamNode(t2, new IdentNode("a"));

  @Test
  public void canGetParams() {
    p = new ParamListNode();
    p.addParam(p1);
    assertTrue(p.getParams().get(0).equals(p1));
  }

  @Test
  public void validWithValidParams() {
    p = new ParamListNode();
    p.addParam(p1);
    p.addParam(p2);
    assertTrue(p.isSemanticallyValid());
  }

  @Test
  public void invalidWithInvalidParam() {
    p = new ParamListNode();
    p.addParam(p3);
    assertFalse(p.isSemanticallyValid());
  }
  
  @Test
  public void invalidWithInvalidParams() {
    p = new ParamListNode();
    p.addParam(p1);
    p.addParam(p2);
    p.addParam(p4);
    assertFalse(p.isSemanticallyValid());
  }
}
