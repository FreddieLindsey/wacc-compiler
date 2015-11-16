package wacc.ast;

import org.junit.Test;

import static org.junit.Assert.*;

public class BasicStatNodeTest {

  private ASTNode parent;
  private BasicStatNode b;
  private StatTypeEnum typeTest = StatTypeEnum.FREE;
  private ExprNode exprTest = new IdentNode(b, "test");

  @Test
  public void basicStatNodeInit() {
    b = new BasicStatNode(parent, typeTest, exprTest);
    assertTrue(b.getExpr().equals(exprTest));
    assertTrue(b.getType().equals(typeTest));
  }

  @Test
  public void basicStatNodeValidity() {
    b = new BasicStatNode(parent, typeTest, exprTest);
    assertTrue(b.isSemanticallyValid() ==
      exprTest.isSemanticallyValid());
  }

}
