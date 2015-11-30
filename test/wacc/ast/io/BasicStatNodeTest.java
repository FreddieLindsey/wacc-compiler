package wacc.ast.io;

import org.junit.Test;
import java.util.ArrayList;

import wacc.ast.type.IntNode;

import static org.junit.Assert.*;


public class BasicStatNodeTest {

  private BasicStatNode node;


  @Test
  public void basicStatSkip() {

    node = new BasicStatNode(StatTypeEnum.SKIP);

    //System.err.println("basicStatSkip: " + node.generateCode());

    assertTrue(node.generateCode().size() == 0);
  }

  @Test
  public void exitInstr() {
    node = new BasicStatNode(StatTypeEnum.EXIT);

    node.addExpr(new IntNode(7));

    //System.err.println("exitInstr: " + node.generateCode());
    //assertTrue(true);

    //need to wait for refactor to change this to string version
    assertTrue(node.generateCode().size() == 3);
  }


}
