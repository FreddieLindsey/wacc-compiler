package wacc.symbolTable;

<<<<<<< 13bee15090abd00d60efafe016aec785e7f780af
import wacc.ast.type.TypeNode;
=======
import wacc.ast.type.TypeEnum;
>>>>>>> Slight refactor

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

  private Map<String, TypeNode> dictionary;
  private SymbolTable parent;

  public SymbolTable() {
    dictionary = new HashMap<>();
  }

  public void add(String s, TypeNode t) {
    dictionary.put(s, t);
  }

  // Looks up s in the current SymbolTable, returns null if not found
  private TypeNode lookUpHere(String s) {
    return dictionary.get(s);
  }

  // Looks up s in the current and enclosing SymbolTables,
  // returns null if not found
  public TypeNode lookUp(String s) {
    SymbolTable current = this;
    TypeNode result;
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
