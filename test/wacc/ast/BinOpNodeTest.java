package wacc.ast;

import org.junit.Test;
import static org.junit.Assert.*;
import wacc.symbolTable.TypeEnum;

public class BinOpNodeTest {

  private ASTNode parent;
  private ExprNode e1;
  private BinaryOperator bo;
  private ExprNode e2;
  private BinOpNode b;

  @Test
  public void canGetFirstExpr() {
    e1 = new ExprNode(b) {
      @Override
      public TypeEnum type() {
        return null;
      }

      @Override
      public boolean isSemanticallyValid() {
        return false;
      }
    };
    b = new BinOpNode(parent, e1, bo, e2);
    assertTrue(b.getLHS().equals(e1));
  }

  @Test
  public void canGetBinaryOperator() {
    bo = BinaryOperator.EQ;
    b = new BinOpNode(parent, e1, bo, e2);
    assertTrue(b.type().equals(TypeEnum.BOOL));
  }

  @Test
  public void canGetSecondExpr() {
    e2 = new ExprNode(b) {
      @Override
      public TypeEnum type() {
        return null;
      }

      @Override
      public boolean isSemanticallyValid() {
        return false;
      }
    };
    b = new BinOpNode(parent, e1, bo, e2);
    assertTrue(b.getRHS().equals(e2));
  }

}
