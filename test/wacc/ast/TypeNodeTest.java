package wacc.ast;

import org.junit.Test;
import wacc.symbolTable.TypeEnum;

public class TypeNodeTest {

  private TypeNode t;

  @Test
  public void canCreateTypeNode() {
    t = new TypeNode(TypeEnum.INT);
  }

}
