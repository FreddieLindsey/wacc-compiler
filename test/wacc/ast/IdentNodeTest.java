package wacc.ast;

import org.junit.Test;
import static org.junit.Assert.*;

public class IdentNodeTest {

  private ASTNode parent;
  private IdentNode id;

  @Test
  public void identNodeInit() {
    id = new IdentNode(parent, "ident");
    assertTrue(id.getIdent() == "ident");
  }

  @Test
  public void identNodeValidityT1() {
    id = new IdentNode(parent, "ident_990");
    assertTrue(id.isSemanticallyValid());
  }

  @Test
  public void identNodeValidityT2() {
    id = new IdentNode(parent, "_JKSHFJK_");
    assertTrue(id.isSemanticallyValid());
  }

  @Test
  public void identNodeValidityF1() {
    id = new IdentNode(parent, "@lkjsdkg");
    assertTrue(!id.isSemanticallyValid());
  }

  @Test
  public void identNodeValidityF2() {
    id = new IdentNode(parent, "1hgklsjkf");
    assertTrue(!id.isSemanticallyValid());
  }

}
