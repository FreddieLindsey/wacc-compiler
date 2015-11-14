package wacc.ast;

import wacc.symbolTable.TypeEnum;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharNodeTest {

  private CharNode c;

  @Test
  public void charNodeInit() {
    c = new CharNode('x');
    assert(c.getValue() == 'x');
  }

  @Test
  public void charNodeValidityT() {
    c = new CharNode('r');
    assert(c.isSemanticallyValid());
  }

  @Test
  public void charNodeValidityF() {
    c = new CharNode('\'');
    assert(!c.isSemanticallyValid());
  }

  @Test
  public void charNodeType() {
    c = new CharNode('r');
    assert(c.type() == TypeEnum.CHAR);
  }

}
