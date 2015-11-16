package wacc.ast.io;

import org.junit.Test;
import static org.junit.Assert.*;

import wacc.ast.ASTNode;
import wacc.ast.ExprNode;
import wacc.ast.StatNode;
import wacc.ast.StatTypeEnum;
import wacc.ast.io.BasicStatNode;
import wacc.ast.io.CompStatNode;
import wacc.ast.type.TypeNode;

public class CompStatNodeTest {

  private CompStatNode c;
  private BasicStatNode s1 = new BasicStatNode(StatTypeEnum.SKIP);
  private BasicStatNode s2 = new BasicStatNode(StatTypeEnum.RETURN); //invalid
  private ExprNode e1 = new ExprNode() {
    @Override
    public TypeNode type() {
      return null;
    }

    @Override
    public boolean isSemanticallyValid() {
      return false;
    }
  };


  @Test
  public void compStatNodeInit() {
    c = new CompStatNode(s1, s2);
    assertTrue(c.getFstStat().equals(s1));
    assertTrue(c.getSndStat().equals(s2));
  }

  @Test
  public void compStatNodeValidity() {
    c = new CompStatNode(s1, s1);
    assertTrue(c.isSemanticallyValid());
  }

  @Test
  public void compStatNodeNotValid() {
    s2.addExpr(e1);
    c = new CompStatNode(s1, s2);
    assertTrue(!c.isSemanticallyValid());
  }

}
