package wacc.ast;

import wacc.symbolTable.TypeEnum;
import org.junit.Test;

public class IntNodeTest {

  private IntNode i;

  @Test
  public void intNodeInit() {
    i = new IntNode(100);
    assert(i.getValue() == 100);
  }

  @Test
  public void intNodeValidity() {
    i = new IntNode(Integer.MAX_VALUE);
    assert(i.isSemanticallyValid());
  }

  @Test
  public void intNodeNotValid() {
    i = new IntNode(Integer.MAX_VALUE * 2);
    assert(!i.isSemanticallyValid());
  }

  @Test
  public void intNodeType() {
    i = new IntNode(Integer.MAX_VALUE);
    assert(i.type() == TypeEnum.INT);
  }

}
