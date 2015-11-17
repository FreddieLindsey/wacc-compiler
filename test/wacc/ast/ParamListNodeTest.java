package wacc.ast;

import org.junit.Test;
import wacc.symbolTable.TypeEnum;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ParamListNodeTest {

  private ParamListNode p;

  private TypeEnum t1 = TypeEnum.BOOL;
  private ParamNode p1 = new ParamNode(t1, new IdentNode("a"));
  private ParamNode p2 = new ParamNode(t1, new IdentNode("b"));
  private ParamNode p3 = new ParamNode(t1, new IdentNode("@lkjsdkg"));

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
    assertTrue(p.isSemanticallyValid());
  }

  @Test
  public void invalidWithInvalidParams() {
    p = new ParamListNode();
    p.addParam(p3);
    assertFalse(p.isSemanticallyValid());
  }
}
