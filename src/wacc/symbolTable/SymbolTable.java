package wacc.symbolTable;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

  private Map<String, TypeEnum> dictionary;
  private SymbolTable parent;

  public SymbolTable() {
    dictionary = new HashMap<>();
  }

  public void add(String s, TypeEnum t) {
    dictionary.put(s, t);
  }

  // Looks up s in the current SymbolTable, returns null if not found
  private TypeEnum lookUpHere(String s) {
    return dictionary.get(s);
  }

  // Looks up s in the current and enclosing SymbolTables,
  // returns null if not found
  public TypeEnum lookUp(String s) {
    SymbolTable current = this;
    TypeEnum result;
    while (current != null) {
      result = current.lookUpHere(s);
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
