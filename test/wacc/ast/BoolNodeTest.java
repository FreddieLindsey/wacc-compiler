package wacc.ast;

<<<<<<< 13bee15090abd00d60efafe016aec785e7f780af
import wacc.ast.type.TypeNode;
import wacc.symbolTable.TypeEnum;
=======
import wacc.ast.type.BoolNode;
import wacc.ast.type.TypeEnum;
>>>>>>> Slight refactor
import org.junit.Test;
import static org.junit.Assert.*;

public class BoolNodeTest {

  private BoolNode b;

  @Test
  public void boolNodeInit() {
      b = new BoolNode(true);
      assertTrue(b.getValue());
  }

  @Test
  public void boolNodeValidity() {
      b = new BoolNode(false);
      assert (b.isSemanticallyValid());
  }

  @Test
  public void boolNodeType() {
      b = new BoolNode(false);
      assert (b.type() == new TypeNode(TypeEnum.BOOL));
  }

}
