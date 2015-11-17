package wacc.symbolTable;

import org.junit.Test;
import wacc.ast.type.TypeNode;

import static org.junit.Assert.*;

public class SymbolTableTest {

  private SymbolTable st = new SymbolTable();
  private SymbolTable stNested = new SymbolTable();

  @Test
  public void canAddSymbolToTable() throws Exception {
    st.add("x", new TypeNode(TypeEnum.INT));
  }

  @Test
  public void canLookUpSymbolInTable() throws Exception {
    st.add("x", new TypeNode(TypeEnum.INT));
    assertTrue(st.lookUp("x").equals(new TypeNode(TypeEnum.INT)));
  }

  @Test
  public void canLookUpInNestedTable() throws Exception {
    st.add("y", new TypeNode(TypeEnum.INT));
    stNested.setParent(st);
    assertTrue(stNested.lookUp("y").equals(new TypeNode(TypeEnum.INT)));
  }
}
