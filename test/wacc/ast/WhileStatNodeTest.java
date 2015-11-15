package wacc.ast;

import org.junit.Test;
import wacc.symbolTable.TypeEnum;

import static org.junit.Assert.*;

public class WhileStatNodeTest {

  private WhileStatNode w;

   private ExprNode e = new BoolNode(true);
   private StatNode s1 = new BasicStatNode(StatTypeEnum.SKIP, null);
   private StatNode s2 = new BasicStatNode(StatTypeEnum.RETURN, null); //invalid

   @Test
   public void whileStatNodeInit() {
     w = new WhileStatNode(e, s1);
     assertTrue(w.getExpr().equals(e));
     assertTrue(w.getStat().equals(s1));
   }

   @Test
   public void whileStatNodeValidT() {
     w = new WhileStatNode(e, s1);
     assertTrue(e.isSemanticallyValid());
     assertTrue(s1.isSemanticallyValid());
     assertTrue(w.isSemanticallyValid());
   }

   @Test
   public void whileStatNodeValidF() {
     w = new WhileStatNode(e, s2);
     assertTrue(e.isSemanticallyValid());
     assertTrue(!s1.isSemanticallyValid());
     assertTrue(!w.isSemanticallyValid());
   }

   @Test
   public void whileStatNodeValidF2() {
     //need to check when expr is not a bool
   }

}