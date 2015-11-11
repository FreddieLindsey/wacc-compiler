package wacc.symbolTable;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

  private SymbolTable parentSymbolTable; // Parent SymbolTable
  private Map<String, TypeEnum> dictionary;

  public SymbolTable(SymbolTable st) {
    parentSymbolTable = st;
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
    TypeEnum result = null;
    while (current != null) {
      result = current.lookUp(s);
      if (result != null) {
        return result;
      }
      current = current.parentSymbolTable;
    }
    return null;
  }

}
