package wacc.ast.io;

import org.junit.Test;
import wacc.ast.type.IntNode;

import static junit.framework.Assert.assertEquals;

public class BasicStatNodeTest {

  private BasicStatNode node;

  @Test
  public void basicStatSkip() {

    node = new BasicStatNode(StatTypeEnum.SKIP);

    //System.err.println("basicStatSkip: " + node.generateCode());

    assertEquals(node.generateCode().size(), 0);
  }

  @Test
  public void exitInstr() {
    node = new BasicStatNode(StatTypeEnum.EXIT);

    node.addExpr(new IntNode(7));

    //System.err.println("exitInstr: " + node.generateCode());
    //assertTrue(true);

    //need to wait for refactor to change this to string version
    assertEquals(node.generateCode().size(), 3);
  }


}
