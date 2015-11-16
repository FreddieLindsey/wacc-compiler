package wacc.ast;

<<<<<<< 13bee15090abd00d60efafe016aec785e7f780af
import wacc.ast.type.TypeNode;
import wacc.symbolTable.TypeEnum;
=======
import wacc.ast.type.CharNode;
import wacc.ast.type.TypeEnum;
>>>>>>> Slight refactor
import org.junit.Test;
import static org.junit.Assert.*;

public class CharNodeTest {

  private CharNode c;

  @Test
  public void charNodeInit() {
      c = new CharNode('x');
      assertTrue(c.getValue().equals('x'));
  }

  @Test
  public void charNodeValidityT() {
      c = new CharNode('r');
      assertTrue(c.isSemanticallyValid());
  }

  @Test
  public void charNodeValidityF() {
      c = new CharNode('\'');
      assertTrue(!c.isSemanticallyValid());
  }

  @Test
  public void charNodeType() {
    c = new CharNode('r');
    assertTrue(c.type().equals(new TypeNode(TypeEnum.CHAR)));
  }

}
