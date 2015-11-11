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

}
