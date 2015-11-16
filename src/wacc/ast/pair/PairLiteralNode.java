package wacc.ast.pair;

<<<<<<< 13bee15090abd00d60efafe016aec785e7f780af:src/wacc/ast/PairLiteralNode.java
import wacc.ast.type.TypeNode;
import wacc.symbolTable.TypeEnum;
=======
import wacc.ast.ASTNode;
import wacc.ast.type.LiteralNode;
import wacc.ast.type.TypeEnum;
>>>>>>> Slight refactor:src/wacc/ast/pair/PairLiteralNode.java

public class PairLiteralNode extends LiteralNode {
  
  public PairLiteralNode() {
    super();
    this.value = null;
    this.type = new TypeNode(TypeEnum.PAIR);
  }

  @Override
  public boolean isSemanticallyValid() {
    return true;
  }
}
