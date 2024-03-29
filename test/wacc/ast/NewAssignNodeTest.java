package wacc.ast;

import org.junit.Test;
import wacc.ast.assign.AssignNode;
import wacc.ast.assign.NewAssignNode;
import wacc.ast.type.TypeEnum;
import wacc.ast.type.TypeNode;

import static org.junit.Assert.assertTrue;

public class NewAssignNodeTest {

  private NewAssignNode n;

  private TypeNode t = new TypeNode(TypeEnum.STRING);
  private IdentNode i = new IdentNode("test");
  private AssignNode rhs = new IdentNode("test2");

  @Test
  public void newAssignNodeInit() {
    n = new NewAssignNode(t, i, rhs);
    assertTrue(n.getType() == t);
    assertTrue(n.getIdent().equals(i));
    assertTrue(n.getRHS().equals(rhs));
  }

}
