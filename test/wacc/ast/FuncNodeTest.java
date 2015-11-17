package wacc.ast;

import wacc.symbolTable.TypeEnum;
import org.junit.Test;
import static org.junit.Assert.*;

public class FuncNodeTest {

  private FuncNode f;

  private ParamNode p1 = new ParamNode(TypeEnum.INT, new IdentNode("a"));
  private ParamNode p2 = new ParamNode(TypeEnum.INT, new IdentNode("b"));
  private ParamNode p3 = new ParamNode(TypeEnum.INT, new IdentNode("@lkjsdkg"));

  private TypeNode t = new TypeNode(TypeEnum.INT);
  private IdentNode n = new IdentNode("x");
  private ParamNode[] ps = new ParamNode[2];
  private StatNode stat1 = new BasicStatNode(StatTypeEnum.SKIP, null);

  private StatNode stat2 = new BasicStatNode(StatTypeEnum.RETURN, null); //invalid
  private IdentNode n1 = new IdentNode("@lkjsdkg"); //invalid

  @Test
  public void canGetType() {
    setParents();
    ps[0] = p1;
    ps[1] = p2;
    ParamListNode p = new ParamListNode(ps);
    f = new FuncNode(t, n, p, stat1);
    assertTrue(f.getType().equals(t));
  }

  @Test
  public void canGetIdent() {
    setParents();
    ps[0] = p1;
    ps[1] = p2;
    ParamListNode p = new ParamListNode(ps);
    f = new FuncNode(t, n, p, stat1);
    assertTrue(f.getIdent().equals(n));
  }

  @Test
  public void canGetParamList() {
    setParents();
    ps[0] = p1;
    ps[1] = p2;
    ParamListNode p = new ParamListNode(ps);
    f = new FuncNode(t, n, p, stat1);
    assertTrue(f.getParams().equals(p));
  }

  @Test
  public void canGetStat() {
    setParents();
    ps[0] = p1;
    ps[1] = p2;
    ParamListNode p = new ParamListNode(ps);
    f = new FuncNode(t, n, p, stat1);
    assertTrue(f.getStat().equals(stat1));
  }

  @Test
  public void funcNodeValidT() {
    setParents();
    ps[0] = p1;
    ps[1] = p2;
    ParamListNode p = new ParamListNode(ps);

    f = new FuncNode(t, n, p, stat1);

    assertTrue(t.isSemanticallyValid());
    assertTrue(n.isSemanticallyValid());
    assertTrue(p.isSemanticallyValid());
    assertTrue(stat1.isSemanticallyValid());
    assertTrue(f.isSemanticallyValid());
  }

  @Test
  public void funcNodeValidF1() {
    setParents();
    ps[0] = p1;
    ps[1] = p2;
    ParamListNode p = new ParamListNode(ps);

    f = new FuncNode(t, n, p, stat2);

    assertTrue(t.isSemanticallyValid());
    assertTrue(n.isSemanticallyValid());
    assertTrue(p.isSemanticallyValid());
    assertFalse(stat2.isSemanticallyValid());
    assertFalse(f.isSemanticallyValid());
  }

  @Test
  public void funcNodeValidF2() {
    setParents();
    ps[0] = p1;
    ps[1] = p3;
    ParamListNode p = new ParamListNode(ps);

    f = new FuncNode(t, n, p, stat1);

    assertTrue(t.isSemanticallyValid());
    assertTrue(n.isSemanticallyValid());
    assertFalse(p.isSemanticallyValid());
    assertTrue(stat1.isSemanticallyValid());
    assertFalse(f.isSemanticallyValid());
  }

  @Test
  public void funcNodeValidF3() {
    setParents();
    ps[0] = p1;
    ps[1] = p2;
    ParamListNode p = new ParamListNode(ps);

    f = new FuncNode(t, n1, p, stat1);

    assertTrue(t.isSemanticallyValid());
    assertFalse(n1.isSemanticallyValid());
    assertTrue(p.isSemanticallyValid());
    assertTrue(stat1.isSemanticallyValid());
    assertFalse(f.isSemanticallyValid());
  }

  private void setParents() {
    p1.getIdent().setParent(p1);
    p2.getIdent().setParent(p2);
    p3.getIdent().setParent(p3);
  }

}
