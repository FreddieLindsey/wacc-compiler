package wacc.ast.type;

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
      assertFalse(c.isSemanticallyValid());
  }

  @Test
  public void charNodeType() {
    c = new CharNode('r');
    assertTrue(c.type().equals(new TypeNode(TypeEnum.CHAR)));
  }

}
