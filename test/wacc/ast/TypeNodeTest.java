package wacc.ast;

import org.junit.Test;
import wacc.symbolTable.TypeEnum;

public class TypeNodeTest {

  private ASTNode parent;
  private TypeNode t;

  @Test
  public void canCreateTypeNode() {
    t = new TypeNode(parent, TypeEnum.INT);
  }

}
