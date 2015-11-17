package wacc.ast;

import wacc.symbolTable.TypeEnum;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProgramNodeTest {

  private final ProgramNode prog = new ProgramNode();

  private FuncNode f;
  private ParamNode p1 = new ParamNode(TypeEnum.INT, new IdentNode("a"));
  private ParamNode p2 = new ParamNode(TypeEnum.INT, new IdentNode("b"));

  private TypeNode t = new TypeNode(TypeEnum.INT);
  private IdentNode n = new IdentNode("x");
  private StatNode stat1 = new BasicStatNode(StatTypeEnum.SKIP, null);

  @Test
  public void canAddFunctionToProgram() {
    f = new FuncNode(t, n, stat1);
    prog.addFunc(f);
  }

  @Test
  public void canCheckIfProgramIsSemanticallyValid() {
    boolean valid = true;

    for (FuncNode f : prog.getFuncs()) {
      valid &= f.isSemanticallyValid();
    }

    StatNode stat = prog.getStat();
    valid &= stat != null && stat.isSemanticallyValid();

    assertTrue(prog.isSemanticallyValid() == valid);
  }

  @Test
  public void canAddStatToProgram() {
    prog.addStat(stat1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void cannotOverWriteStatOfProgram() {
    prog.addStat(stat1);
    prog.addStat(stat1);
  }

}
