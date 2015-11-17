package wacc.ast.type;

import org.junit.Test;

public class TypeNodeTest {

  private TypeNode t;

  @Test
  public void canCreateTypeNode() {
    t = new TypeNode(TypeEnum.INT);
  }

}
