package wacc.ast;

import org.junit.Test;
import static org.junit.Assert.*;

import wacc.symbolTable.TypeEnum;

public class ParamNodeTest {

  private ASTNode parent;
  private ParamNode param;
  private TypeEnum type = TypeEnum.INT;
  private IdentNode id1 = new IdentNode(param, "ident");
  private IdentNode id2 =  new IdentNode(param, "@lkjsdkg");

  @Test
  public void canGetType() {
    param = new ParamNode(parent, type, id1);
    assertTrue(param.getType().equals(type));
  }

  @Test
  public void canGetIdent() {
    param = new ParamNode(parent, type, id1);
    assertTrue(param.getIdent().equals(id1));
  }

  @Test
  public void paramNodeIsValid() {
    param = new ParamNode(parent, type, id1);
    assertTrue(param.isSemanticallyValid());
  }

  @Test
  public void paramNodeIsInvalid() {
    param = new ParamNode(parent, type, id2);
    assertFalse(param.isSemanticallyValid());
  }

}
