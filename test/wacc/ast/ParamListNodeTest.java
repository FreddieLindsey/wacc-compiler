package wacc.ast;

import org.junit.Test;
<<<<<<< 13bee15090abd00d60efafe016aec785e7f780af
import wacc.symbolTable.TypeEnum;
=======
import wacc.ast.function.ParamListNode;
import wacc.ast.function.ParamNode;
import wacc.ast.type.TypeEnum;
>>>>>>> Slight refactor

import static org.junit.Assert.*;

public class ParamListNodeTest {

  private ParamListNode p;

  private TypeNode t1 = new TypeNode(TypeEnum.BOOL);
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
