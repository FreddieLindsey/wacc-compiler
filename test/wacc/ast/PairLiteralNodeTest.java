package wacc.ast;

import org.junit.Test;
<<<<<<< 13bee15090abd00d60efafe016aec785e7f780af
import wacc.symbolTable.TypeEnum;
=======
import wacc.ast.pair.PairLiteralNode;
>>>>>>> Slight refactor

import static org.junit.Assert.*;

public class PairLiteralNodeTest {

  private final PairLiteralNode p = new PairLiteralNode();

  @Test
  public void assertPairLiteralIsNull() {
    assertFalse(p.getValue() != null);
  }

  @Test
  public void hasTypePair() {
    assertTrue(p.type().equals(new TypeNode(null, null)));
  }

}
