package wacc.ast;

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
        c = new CharNode('\f');
        assert(!c.isSemanticallyValid());
    }

}
