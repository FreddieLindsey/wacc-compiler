package wacc.symbolTable;

import wacc.ast.type.TypeNode;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

  private Map<String, DataContainer> dictionary;
  private SymbolTable parent;

  public SymbolTable() {
    dictionary = new HashMap<>();
  }

  public void add(String s, TypeNode t) {
    dictionary.put(s, new DataContainer(t));
  }

  // Looks up s in the current SymbolTable, returns null if not found
  public TypeNode lookUpHereType(String s) {
    return dictionary.get(s).getTypeNode();
  }

  // Looks up s in the current and enclosing SymbolTables,
  // returns null if not found
  public TypeNode lookUpType(String s) {
    SymbolTable current = this;
    TypeNode result;
    while (current != null) {
      result = current.lookUpHereType(s);
      if (result != null) {
        return result;
      }
      current = current.parent;
    }
    return null;
  }

  public void setParent(SymbolTable parent) {
    this.parent = parent;
  }
}
