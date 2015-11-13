package wacc.ast;

import org.junit.Test;

public class CompStatNodeTest {

	StatNode s1 = new StatNode(TypeNodeEnum.SKIP, null);
  StatNode s2 = new StatNode(TypeNodeEnum.SKIP, null);
  CompStatNode c;


  @Test
  public void compStatNodeInit() {
    c = new CompStatNode(s1, s2);
    assert(c.getFstStat().equals(s1));
    assert(c.getSndStat().equals(s2));
  }

  @Test
  public void compStatNodeValidity() {
      c = new CompStatNode(s1, s2);
      assert(c.isSemanticallyValid());
  }

  // @Test
  // public void compStatNodeNotValid() {
  //     c = new CompStatNode(s1, s2);
  //     assert(!c.isSemanticallyValid());
  // }

}
