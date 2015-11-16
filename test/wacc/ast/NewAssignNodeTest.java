package wacc.ast;

import org.junit.Test;
import static org.junit.Assert.*;

<<<<<<< 13bee15090abd00d60efafe016aec785e7f780af
import wacc.symbolTable.TypeEnum;
=======
import wacc.ast.type.TypeEnum;
>>>>>>> Slight refactor

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
