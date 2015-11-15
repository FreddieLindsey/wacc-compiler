package wacc.ast;

import org.junit.Test;
import wacc.symbolTable.TypeEnum;

import static org.junit.Assert.*;

public class PairLiteralNodeTest {

  private static final PairLiteralNode p = new PairLiteralNode();

  @Test
  public void assertPairLiteralIsNull() {
    assertFalse(p.getValue() != null);
  }

  @Test
  public void hasTypePair() {
    assertTrue(p.type().equals(TypeEnum.PAIR));
  }

}
