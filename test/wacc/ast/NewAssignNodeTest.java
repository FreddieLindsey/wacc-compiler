package wacc.ast;

import org.junit.Test;
import static org.junit.Assert.*;

import wacc.symbolTable.TypeEnum;

public class NewAssignNodeTest {

  private NewAssignNode n;

  private TypeEnum t = TypeEnum.STRING;
  private IdentNode i = new IdentNode("test");
  private AssignRHSNode rhs = new IdentNode("test2");

  @Test
  public void newAssignNodeInit() {
    n = new NewAssignNode(t, i, rhs);
    assertTrue(n.getType() == t);
    assertTrue(n.getIdent().equals(i));
    assertTrue(n.getRHS().equals(rhs));
  }

  @Test
  public void newAssignNodeValid() {
    n = new NewAssignNode(t, i, rhs);
    assertTrue(n.isSemanticallyValid() ==
          (
            i.isSemanticallyValid()
            && rhs.isSemanticallyValid()
            && t == rhs.type()
          ));
  }

}
