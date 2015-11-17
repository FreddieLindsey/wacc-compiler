package wacc.ast;

import org.junit.Test;

import static org.junit.Assert.*;

import wacc.ast.type.TypeNode;
import wacc.symbolTable.TypeEnum;

public class BinOpNodeTest {

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
  private BinaryOperator bo = BinaryOperator.EQ;
  private ExprNode e2 = new ExprNode() {
    @Override
    public TypeNode type() {
      return null;
    }

    @Override
    public boolean isSemanticallyValid() {
      return false;
    }
  };
  private BinOpNode b;

  @Test
  public void canGetFirstExpr() {
    b = new BinOpNode(bo);
    b.addLHS(e1);
    b.addRHS(e2);
    assertTrue(b.getLHS().equals(e1));
  }

  @Test
  public void canGetTypeOfBinaryOperator() {
    b = new BinOpNode(bo);
    b.addLHS(e1);
    b.addRHS(e2);
    assertTrue(b.type().equals(TypeEnum.BOOL));
  }

  @Test
  public void canGetSecondExpr() {
    b = new BinOpNode(bo);
    b.addLHS(e1);
    b.addRHS(e2);
    assertTrue(b.getRHS().equals(e2));
  }

}
