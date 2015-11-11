package wacc.symbolTable;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

  private SymbolTable symbolTable; // Parent SymbolTable
  private Map<String, TypeEnum> dictionary;

  public SymbolTable(SymbolTable st) {
    symbolTable = st;
    dictionary = new HashMap<String, TypeEnum>();
  }

  public void add(String s, TypeEnum t) {
    dictionary.put(s, t);
  }

  // Looks up s in the current SymbolTable, returns null if not found
  // TODO: should this be private?
  public TypeEnum lookUp(String s) {
    return dictionary.get(s);
  }

  // Looks up s in the current and enclosing SymbolTables,
  // returns null if not found
  public TypeEnum lookUpAll(String s) {
    SymbolTable currTable = this;
    TypeEnum result = null;
    while (currTable != null) {
      result = currTable.lookUp(s);
      if (result != null) {
        return result;
      }
      currTable = currTable.symbolTable;
    }
    return null;
  }
  
}
