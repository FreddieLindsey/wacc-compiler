package wacc.ast;

import org.junit.Test;

import wacc.symbolTable.TypeEnum;

public class ReAssignNodeTest {

  private NewAssignNode n;

  private TypeEnum t = TypeEnum.STRING;
  private IdentNode i = new IdentNode("test");
  private AssignRHSNode rhs = new IdentNode("test2");

  @Test
  public void newAssignNodeInit() {
    n = new NewAssignNode(t, i, rhs);
    assert(n.getType() == t);
    assert(n.getIdent().equals(i));
    assert(n.getRHS().equals(rhs));
  }

  @Test
  public void newAssignNodeValid() {
    n = new NewAssignNode(t, i, rhs);
    assert(n.isSemanticallyValid() ==
          (
            i.isSemanticallyValid()
            && rhs.isSemanticallyValid()
            && t == rhs.type()
          ));
  }

}
