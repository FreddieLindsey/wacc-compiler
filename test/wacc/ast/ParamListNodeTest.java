package wacc.ast;

import org.junit.Test;
import wacc.symbolTable.TypeEnum;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ParamListNodeTest {

  private ParamListNode p;
  private Set<ParamNode> ps = new HashSet<>();

  private TypeNode t1 = new TypeNode(TypeEnum.BOOL);
  private ParamNode p1 = new ParamNode(t1, new IdentNode("b"));

  @Test
  public void canGetParams() {
    ps.add(p1);
    p = new ParamListNode(ps);
    assertTrue(p.getParams().equals(ps));
  }
}
