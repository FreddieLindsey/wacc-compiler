package wacc.ast;

import org.junit.Test;
import static org.junit.Assert.*;

import wacc.ast.StatTypeEnum;

public class CompStatNodeTest {

  StatNode s1 = new BasicStatNode(StatTypeEnum.SKIP, null);
  StatNode s2 = new BasicStatNode(StatTypeEnum.SKIP, null);
  StatNode s3 = new BasicStatNode(StatTypeEnum.RETURN, null); //invalid
  CompStatNode c;


  @Test
  public void compStatNodeInit() {
    c = new CompStatNode(s1, s2);
    assertTrue(c.getFstStat().equals(s1));
    assertTrue(c.getSndStat().equals(s2));
  }

  @Test
  public void compStatNodeValidity() {
    c = new CompStatNode(s1, s2);
    assertTrue(c.isSemanticallyValid());
  }

  @Test
  public void compStatNodeNotValid() {
    c = new CompStatNode(s1, s3);
    assertTrue(!c.isSemanticallyValid());
  }

}
