package wacc.ast;

import org.junit.Test;
import static org.junit.Assert.*;

import wacc.ast.StatTypeEnum;
import wacc.symbolTable.TypeEnum;

public class CompStatNodeTest {

  private CompStatNode c;
  private StatNode s1 = new BasicStatNode(StatTypeEnum.SKIP);
  private StatNode s2 = new BasicStatNode(StatTypeEnum.RETURN); //invalid
  private ExprNode e1 = new ExprNode() {
    @Override
    public TypeEnum type() {
      return null;
    }

    @Override
    public boolean isSemanticallyValid() {
      return false;
    }
  };


  @Test
  public void compStatNodeInit() {
    setParents();
    c = new CompStatNode(s1, s2);
    assertTrue(c.getFstStat().equals(s1));
    assertTrue(c.getSndStat().equals(s2));
  }

  @Test
  public void compStatNodeValidity() {
    setParents();
    c = new CompStatNode(s1, s2);
    assertTrue(c.isSemanticallyValid());
  }

  @Test
  public void compStatNodeNotValid() {
    setParents();
    c = new CompStatNode(s1, s3);
    assertTrue(!c.isSemanticallyValid());
  }

  private void setParents() {
    ((BasicStatNode) s3).getExpr().setParent(s3);
  }

}
