package wacc.ast;

import org.junit.Test;
import wacc.symbolTable.TypeEnum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ParamListNodeTest {

  private ParamListNode p;
  private ParamNode[] ps = new ParamNode[2];

  private TypeNode t1 = new TypeNode(TypeEnum.BOOL);
  private ParamNode p1 = new ParamNode(t1, new IdentNode("b"));

  @Test
  public void canGetParams() {
    ps[0] = p1;
    ps[1] = p1;
    p = new ParamListNode(ps);
    assertTrue(Arrays.equals(p.getParams(), ps));
  }
}
