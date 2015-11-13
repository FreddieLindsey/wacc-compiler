package wacc.ast;

import org.junit.Test;

public class BasicStatNodeTest {

    private BasicStatNode b;

    private StatTypeEnum typeTest = StatTypeEnum.FREE;
    private ExprNode exprTest = new IdentNode("test");

    @Test
    public void basicStatNodeInit() {
        b = new BasicStatNode(typeTest, exprTest);
        assert(b.getExpr().equals(exprTest));
        assert(b.getType().equals(typeTest));
    }

    @Test
    public void basicStatNodeValidity() {
        b = new BasicStatNode(typeTest, exprTest);
        assert(b.isSemanticallyValid() ==
                exprTest.isSemanticallyValid());
    }

}
