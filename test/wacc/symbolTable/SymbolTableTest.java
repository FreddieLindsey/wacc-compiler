package wacc.symbolTable;

import org.junit.Test;

import static org.junit.Assert.*;

public class SymbolTableTest {

  private SymbolTable st = new SymbolTable();
  private SymbolTable stNested = new SymbolTable();

  @Test
  public void canAddSymbolToTable() throws Exception {
    st.add("x", TypeEnum.INT);
  }

  @Test
  public void canLookUpSymbolInTable() throws Exception {
    st.add("x", TypeEnum.INT);
    assertTrue(st.lookUp("x") == TypeEnum.INT);
  }

  @Test
  public void canLookUpInNestedTable() throws Exception {
    st.add("y", TypeEnum.INT);
    stNested.setParent(st);
    assertTrue(stNested.lookUp("y") == TypeEnum.INT);
  }
}
