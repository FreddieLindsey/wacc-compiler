package wacc.ast;

import wacc.symbolTable.TypeEnum;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProgramNodeTest {

  private ASTNode parent;
  private final ProgramNode prog = new ProgramNode(parent);

  private FuncNode f;
  private ParamListNode p;
  private ParamNode[] ps = new ParamNode[2];
  private ParamNode p1 = new ParamNode(p, TypeEnum.INT, new IdentNode(null, "a"));
  private ParamNode p2 = new ParamNode(p, TypeEnum.INT, new IdentNode(null, "b"));

  private TypeNode t = new TypeNode(f, TypeEnum.INT);
  private IdentNode n = new IdentNode(f, "x");
  private StatNode stat1 = new BasicStatNode(f, StatTypeEnum.SKIP, null);

  @Test
  public void canAddFunctionToProgram() {
    ps[0] = p1;
    ps[1] = p2;
    p = new ParamListNode(f, ps);
    f = new FuncNode(prog, t, n, p, stat1);
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

    assertTrue(prog.isSemanticallyValid() == valid);
  }

  @Test
  public void canAddStatToProgram() {
    prog.setStat(stat1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void cannotOverWriteStatOfProgram() {
    prog.setStat(stat1);
    prog.setStat(stat1);
  }

  private void setParents() {
    p1.getIdent().setParent(p1);
    p2.getIdent().setParent(p2);
  }

}
