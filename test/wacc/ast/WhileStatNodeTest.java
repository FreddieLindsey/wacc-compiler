package wacc.ast;

import org.junit.Test;
import wacc.symbolTable.TypeEnum;

public class WhileStatNodeTest {

  private WhileStatNode w;

  @Test
  public void removeThis() {
    assert(true);
  }

  // private ExprNode e = BoolNode(true);
  // private StatNode s1 = new BasicStatNode(StatTypeEnum.SKIP, null);
  // private StatNode s2 = new BasicStatNode(StatTypeEnum.RETURN, null); //invalid

  // @Test
  // public void whileStatNodeInit() {
  //   w = new WhileStatNode(e, s1);
  //   assert(w.getExpr().equals(e));
  //   assert(w.getStat().equals(s1));
  // }

  // @Test
  // public void whileStatNodeValidT() {
  //   w = new WhileStatNode(e, s1);
  //   assert(e.isSemanticallyValid());
  //   assert(s1.isSemanticallyValid());
  //   assert(w.isSemanticallyValid());
  // }

  // @Test
  // public void whileStatNodeValidF() {
  //   w = new WhileStatNode(e, s2);
  //   assert(e.isSemanticallyValid());
  //   assert(!s1.isSemanticallyValid());
  //   assert(!w.isSemanticallyValid());
  // }

  // @Test
  // public void whileStatNodeValidF2() {
  //   //need to check when expr is not a bool
  // }

}