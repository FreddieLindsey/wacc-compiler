package wacc.ast;

import org.junit.Test;
import wacc.ast.io.BasicStatNode;

import static org.junit.Assert.*;

public class BasicStatNodeTest {

  private BasicStatNode b;
  private StatTypeEnum typeTest = StatTypeEnum.FREE;
  private ExprNode exprTest = new IdentNode("test");

  @Test
  public void basicStatNodeInit() {
    b = new BasicStatNode(typeTest);
    b.addExpr(exprTest);
    assertTrue(b.getExpr().equals(exprTest));
    assertTrue(b.getType().equals(typeTest));
  }

  @Test
  public void basicStatNodeValidity() {
    b = new BasicStatNode(typeTest);
    b.addExpr(exprTest);
    assertTrue(b.isSemanticallyValid() ==
      exprTest.isSemanticallyValid());
  }

}
