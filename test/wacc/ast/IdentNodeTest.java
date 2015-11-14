package wacc.ast;

import org.junit.Test;
import static org.junit.Assert.*;

public class IdentNodeTest {

  private IdentNode id;

  @Test
  public void identNodeInit() {
    id = new IdentNode("ident");
    assertTrue(id.getIdent() == "ident");
  }

  @Test
  public void identNodeValidityT1() {
    id = new IdentNode("ident_990");
    assertTrue(id.isSemanticallyValid());
  }

  @Test
  public void identNodeValidityT2() {
    id = new IdentNode("_JKSHFJK_");
    assertTrue(id.isSemanticallyValid());
  }

  @Test
  public void identNodeValidityF1() {
    id = new IdentNode("@lkjsdkg");
    assertTrue(!id.isSemanticallyValid());
  }

  @Test
  public void identNodeValidityF2() {
    id = new IdentNode("1hgklsjkf");
    assertTrue(!id.isSemanticallyValid());
  }

}
