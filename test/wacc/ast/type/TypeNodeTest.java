package wacc.ast.type;

import org.junit.Test;
<<<<<<< 13bee15090abd00d60efafe016aec785e7f780af:test/wacc/ast/type/TypeNodeTest.java
import wacc.symbolTable.TypeEnum;
=======
>>>>>>> Slight refactor:test/wacc/ast/TypeNodeTest.java

public class TypeNodeTest {

  private TypeNode t;

  @Test
  public void canCreateTypeNode() {
    t = new TypeNode(TypeEnum.INT);
  }

}
