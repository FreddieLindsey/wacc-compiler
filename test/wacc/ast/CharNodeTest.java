package wacc.ast;

import wacc.symbolTable.TypeEnum;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.*;

public class CharNodeTest {

  private ASTNode parent;
  private CharNode c;

  @Test
  public void charNodeInit() {
      c = new CharNode(parent, 'x');
      assertTrue(c.getValue().equals('x'));
  }

  @Test
  public void charNodeValidityT() {
      c = new CharNode(parent, 'r');
      assertTrue(c.isSemanticallyValid());
  }

  @Test
  public void charNodeValidityF() {
      c = new CharNode(parent, '\'');
      assertTrue(!c.isSemanticallyValid());
  }

  @Test
  public void charNodeType() {
    c = new CharNode(parent, 'r');
    assertTrue(c.type() == TypeEnum.CHAR);
  }

}
