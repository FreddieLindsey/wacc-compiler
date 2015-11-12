package wacc.ast;

import org.junit.Test;

public class BoolNodeTest {

    private BoolNode b;

    @Test
    public void boolNodeInit() {
        b = new BoolNode(true);
        assert(b.getValue());
    }

    @Test
    public void boolNodeValidity() {
        b = new BoolNode(false);
        assert (b.isSemanticallyValid());
    }

}
