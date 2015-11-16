package wacc.ast;

import org.junit.Test;
import static org.junit.Assert.*;

<<<<<<< 13bee15090abd00d60efafe016aec785e7f780af
import wacc.symbolTable.TypeEnum;
=======
import wacc.ast.function.ParamNode;
import wacc.ast.type.TypeEnum;
>>>>>>> Slight refactor

public class ParamNodeTest {

  private ParamNode param;
  private TypeNode type = new TypeNode(TypeEnum.INT);
  private IdentNode id1 = new IdentNode("ident");
  private IdentNode id2 =  new IdentNode("@lkjsdkg");

  @Test
  public void canGetType() {
    param = new ParamNode(type, id1);
    assertTrue(param.getType().equals(type));
  }

  @Test
  public void canGetIdent() {
    param = new ParamNode(type, id1);
    assertTrue(param.getIdent().equals(id1));
  }

  @Test
  public void paramNodeIsValid() {
    param = new ParamNode(type, id1);
    assertTrue(param.isSemanticallyValid());
  }

  @Test
  public void paramNodeIsInvalid() {
    param = new ParamNode(type, id2);
    assertFalse(param.isSemanticallyValid());
  }

}
