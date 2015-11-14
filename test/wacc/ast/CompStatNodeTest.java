package wacc.ast;

import org.junit.Test;
import static org.junit.Assert.*;

import wacc.ast.StatTypeEnum;
import wacc.symbolTable.TypeEnum;

public class CompStatNodeTest {

  private StatNode s1 = new BasicStatNode(StatTypeEnum.SKIP, null);
  private StatNode s2 = new BasicStatNode(StatTypeEnum.SKIP, null);
  private StatNode s3 = new BasicStatNode(StatTypeEnum.RETURN, new ExprNode() {
    @Override
    public TypeEnum type() {
      return null;
    }

    @Override
    public boolean isSemanticallyValid() {
      return false;
    }
  }); //invalid
  private CompStatNode c;


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
