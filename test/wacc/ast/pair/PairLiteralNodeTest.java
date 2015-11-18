package wacc.ast.pair;

import org.junit.Test;
import wacc.ast.type.PairTypeNode;
import wacc.ast.type.TypeNode;

import static org.junit.Assert.*;

public class PairLiteralNodeTest {

  private final PairLiteralNode p = new PairLiteralNode();

  @Test
  public void assertPairLiteralIsNull() {
    assertFalse(p.getValue() != null);
  }

  @Test
  public void hasTypePair() {
    assertTrue(p.type().equals(new PairTypeNode(null, null)));
  }

}
