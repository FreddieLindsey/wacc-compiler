package wacc.ast;

import wacc.symbolTable.TypeEnum;
import org.junit.Test;

public class StringNodeTest {

  private StringNode s;

  @Test
  public void stringNodeInit() {
    s = new StringNode("test");
    assert(s.getValue() == "test");
  }

  @Test
  public void stringNodeValidityT() {
    s = new StringNode("test");
    assert(s.isSemanticallyValid());
  }

  @Test
  public void stringNodeValidityF() {
    s = new StringNode("tes\t");
    assert(!s.isSemanticallyValid());
  }

  @Test
  public void stringNodeType() {
    s = new StringNode("test");
    assert(s.type() == TypeEnum.STRING);
  }

}
