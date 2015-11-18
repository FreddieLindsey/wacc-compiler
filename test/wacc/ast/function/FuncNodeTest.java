package wacc.ast.function;

import wacc.ast.type.FuncTypeNode;
import wacc.ast.type.TypeNode;
import wacc.ast.ASTNode;
import wacc.ast.IdentNode;
import wacc.ast.StatNode;
import wacc.ast.StatTypeEnum;
import wacc.ast.function.FuncNode;
import wacc.ast.function.ParamListNode;
import wacc.ast.function.ParamNode;
import wacc.ast.io.BasicStatNode;
import wacc.ast.type.TypeNode;
import wacc.ast.type.TypeEnum;
import org.junit.Test;
import static org.junit.Assert.*;

public class FuncNodeTest {

  private FuncNode f;
  private FuncTypeNode fType;

  private ParamNode p1 = new ParamNode(new TypeNode(TypeEnum.INT), new IdentNode("a"));
  private ParamNode p2 = new ParamNode(new TypeNode(TypeEnum.INT), new IdentNode("b"));
  private ParamNode p3 = new ParamNode(new TypeNode(TypeEnum.INT), new IdentNode("@lkjsdkg"));

  private TypeNode t = new TypeNode(TypeEnum.INT);
  private IdentNode n = new IdentNode("x");
  private BasicStatNode stat1 = new BasicStatNode(StatTypeEnum.SKIP);

  private BasicStatNode stat2 = new BasicStatNode(StatTypeEnum.RETURN); //invalid
  private IdentNode n1 = new IdentNode("@lkjsdkg"); //invalid

  @Test
  public void canGetType() {
    f = new FuncNode(t, n, stat1);
    fType = new FuncTypeNode(t);
    assertTrue(f.getType().equals(fType));
  }

  @Test
  public void canGetIdent() {
    f = new FuncNode(t, n, stat1);
    assertTrue(f.getIdent().equals(n));
  }

  @Test
  public void canGetParamList() {
    f = new FuncNode(t, n, stat1);
    f.addParam(p1);
    assertTrue(f.getParams().getParams().get(0).equals(p1));
  }

  @Test
  public void canGetStat() {
    f = new FuncNode(t, n, stat1);
    assertTrue(f.getStat().equals(stat1));
  }

  @Test
  public void funcNodeValidT() {
    f = new FuncNode(t, n, stat1);
    f.addParam(p1);
    assertTrue(t.isSemanticallyValid());
    assertTrue(n.isSemanticallyValid());
    assertTrue(f.getParams().isSemanticallyValid());
    assertTrue(stat1.isSemanticallyValid());
    assertTrue(f.isSemanticallyValid());
  }

  @Test
  public void funcNodeValidF1() {
    f = new FuncNode(t, n, stat2);
    f.addParam(p1);

    assertTrue(t.isSemanticallyValid());
    assertTrue(n.isSemanticallyValid());
    assertTrue(f.getParams().isSemanticallyValid());
    assertFalse(stat2.isSemanticallyValid());
    assertFalse(f.isSemanticallyValid());
  }

  @Test
  public void funcNodeValidF2() {
    f = new FuncNode(t, n, stat1);
    f.addParam(p1);
    f.addParam(p1);

    assertTrue(t.isSemanticallyValid());
    assertTrue(n.isSemanticallyValid());
    assertFalse(f.getParams().isSemanticallyValid());
    assertTrue(stat1.isSemanticallyValid());
    assertFalse(f.isSemanticallyValid());
  }

  @Test
  public void funcNodeValidF3() {
    f = new FuncNode(t, n1, stat1);
    f.addParam(p1);

    assertTrue(t.isSemanticallyValid());
    assertFalse(n1.isSemanticallyValid());
    assertTrue(f.getParams().isSemanticallyValid());
    assertTrue(stat1.isSemanticallyValid());
    assertFalse(f.isSemanticallyValid());
  }

  @Test
  public void funcNodeValidF4() {
    f = new FuncNode(t, n, stat1);
    f.addParam(p1);
    f.addParam(p3);

    assertTrue(t.isSemanticallyValid());
    assertTrue(n.isSemanticallyValid());
    assertFalse(f.getParams().isSemanticallyValid());
    assertTrue(stat1.isSemanticallyValid());
    assertFalse(f.isSemanticallyValid());
  }

}
