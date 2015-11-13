package wacc.ast;

import org.junit.Test;

import wacc.symbolTable.TypeEnum;

public class ParamNodeTest {

  private ParamNode param;
  private TypeNode type = new TypeNode(TypeEnum.INT);
  private IdentNode id = new IdentNode("ident");

  @Test
  public void paramNodeInit() {
    param = new ParamNode(type, id);
    assert(param.getType().equals(type));
    assert(param.getIdent().equals(id));
  }

  @Test
  public void paramNodeIsSemanticallyValid() {
    param = new ParamNode(type, id);
    assert(param.isSemanticallyValid() ==
            (type.isSemanticallyValid() && id.isSemanticallyValid()));
  }

}
