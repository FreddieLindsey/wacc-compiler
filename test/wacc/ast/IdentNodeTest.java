package wacc.ast;

import org.junit.Test;

public class IdentNodeTest {
  
  private IdentNode id;
  
  @Test
  public void identNodeInit() {
    id = new IdentNode("ident");
    assert(id.getIdent() == "ident");
  }
  
  @Test
  public void identNodeValidityT1() {
    id = new IdentNode("ident_990");
    assert(id.isSemanticallyValid());
  }
  
  @Test
  public void identNodeValidityT2() {
    id = new IdentNode("_JKSHFJK_");
    assert(id.isSemanticallyValid());
  }

  @Test
  public void identNodeValidityF1() {
    id = new IdentNode("@lkjsdkg");
    assert(!id.isSemanticallyValid());
  }
  
  @Test
  public void identNodeValidityF2() {
    id = new IdentNode("1hgklsjkf");
    assert(!id.isSemanticallyValid());
  }
  
}
