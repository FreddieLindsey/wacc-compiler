package wacc.ast;

import org.junit.Test;
import wacc.symbolTable.TypeEnum;

import static org.junit.Assert.*;

public class PairLiteralNodeTest {

  private ASTNode parent;
  private final PairLiteralNode p = new PairLiteralNode(parent);

  @Test
  public void assertPairLiteralIsNull() {
    assertFalse(p.getValue() != null);
  }

  @Test
  public void hasTypePair() {
    assertTrue(p.type().equals(TypeEnum.PAIR));
  }

}
