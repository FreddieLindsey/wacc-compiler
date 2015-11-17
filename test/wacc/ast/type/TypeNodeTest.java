package wacc.ast.type;

import org.junit.Test;
import wacc.ast.type.TypeNode;
import wacc.symbolTable.TypeEnum;

public class TypeNodeTest {

  private TypeNode t;

  @Test
  public void canCreateTypeNode() {
    t = new TypeNode(TypeEnum.INT);
  }

}
