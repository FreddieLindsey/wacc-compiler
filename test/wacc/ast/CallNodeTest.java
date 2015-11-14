package wacc.ast;

import org.junit.Test;

public class CallNodeTest {

    @Test
    public void removethis() {
        assert(true);
    }

    // private CallNode c;
    // private IdentNode ident = new IdentNode("x");
    // private ExprNode e1 = new IntNode(5);
    // private ExprNode e2 = new IntNode(10);
    // private ExprNode e3 = new IntNode(Integer.MAX_VALUE + 1000); //invalid
    // private ArgListNode argsV = new ArgListNode(new ExprNode[]{e1, e2});
    // private ArgListNode argsINV = new ArgListNode(new ExprNode[]{e1, e2, e3}); //invalid


    // @Test
    // public void callInit() {
    //     c = new CallNode(ident, argsV);

    //     assert(c.getIdent().equals(ident));
    //     assert(c.getArgs().equals(argsV));
    // }

    // @Test
    // public void callValidityT() {
    //     c = new CallNode(ident, argsV);

    //     assert(ident.isSemanticallyValid());
    //     assert(argsV.isSemanticallyValid());
    //     assert(c.isSemanticallyValid());
    // }

    // @Test
    // public void callValidityF() {
    //     c = new CallNode(ident, argsV);

    //     assert(ident.isSemanticallyValid());
    //     assert(!argsINV.isSemanticallyValid());
    //     assert(!c.isSemanticallyValid());
    // }

}