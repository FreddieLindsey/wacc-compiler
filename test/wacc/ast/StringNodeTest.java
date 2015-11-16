package wacc.ast;

<<<<<<< 13bee15090abd00d60efafe016aec785e7f780af
import wacc.ast.type.TypeNode;
import wacc.symbolTable.TypeEnum;
=======
import wacc.ast.type.StringNode;
import wacc.ast.type.TypeEnum;
>>>>>>> Slight refactor
import org.junit.Test;
import static org.junit.Assert.*;

public class StringNodeTest {

  private StringNode s;

  @Test
  public void stringNodeInit() {
    s = new StringNode("test");
    assertTrue(s.getValue().equals("test"));
  }

  @Test
  public void stringNodeValidityT() {
    s = new StringNode("test");
    assertTrue(s.isSemanticallyValid());
  }

  @Test
  public void stringNodeValidityF() {
    s = new StringNode("tes\\t");
    assertFalse(s.isSemanticallyValid());
  }

  @Test
  public void stringNodeType() {
    s = new StringNode("test");
    assertTrue(s.type().equals(new TypeNode(TypeEnum.STRING)));
  }
}
