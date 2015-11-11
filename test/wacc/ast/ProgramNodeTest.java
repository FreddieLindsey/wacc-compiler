package wacc.ast;

import org.junit.Test;

public class ProgramNodeTest {

    private final ProgramNode prog = new ProgramNode();
    private final StatNode stat = new StatNode() {
        @Override
        public boolean isSemanticallyValid() {
            return false;
        }
    };

    @Test
    public void canAddFunctionToProgram() {
        FuncNode f = new FuncNode();
        prog.add(f);
    }

    @Test
    public void canCheckIfProgramIsSemanticallyValid() {
        boolean valid = true;

        for (FuncNode f : prog.getFuncs()) {
            valid &= f.isSemanticallyValid();
        }

        StatNode stat = prog.getStat();
        valid &= stat != null && stat.isSemanticallyValid();

        assert(prog.isSemanticallyValid() == valid);
    }

    @Test
    public void canAddStatToProgram() {
        prog.setStat(stat);
    }

    @Test(expected=IllegalArgumentException.class)
    public void cannotOverWriteStatOfProgram() {
        prog.setStat(stat);
    }

}