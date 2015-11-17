package wacc.symbolTable;

import org.junit.Test;
import wacc.ast.type.TypeEnum;
import wacc.ast.type.TypeNode;

import static org.junit.Assert.*;

public class SymbolTableTest {

  private SymbolTable st = new SymbolTable();
  private SymbolTable stNested = new SymbolTable();
  private TypeNode bob = new TypeNode(TypeEnum.INT);

  @Test
  public void canAddSymbolToTable() throws Exception {
    st.add("x", bob);
  }

  @Test
  public void canLookUpSymbolInTable() throws Exception {
    st.add("x", bob);
    assertTrue(st.lookUp("x").equals(bob));
  }

  @Test
  public void canLookUpInNestedTable() throws Exception {
    st.add("y", bob);
    stNested.setParent(st);
    assertTrue(stNested.lookUp("y").equals(bob));
  }
}
