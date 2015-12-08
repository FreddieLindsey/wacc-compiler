package wacc.symbolTable;

import com.sun.jndi.cosnaming.IiopUrl;
import wacc.ast.type.TypeNode;
import wacc.symbolTable.DataContainer.*;

import javax.xml.crypto.Data;
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
    return getDataContainer(s).getTypeNode();
  }

  // Looks up s in the current SymbolTable, returns null if not found
  public AddressReference lookUpHereAddressReference(String s) {
    return getDataContainer(s).getAddressReference();
  }

  // Looks up s in the current and enclosing SymbolTables,
  // returns null if not found
  public TypeNode lookUpType(String s) {
    DataContainer d = lookUpDataFor(s);
    return (d != null) ? d.getTypeNode() : null;
  }

  // Looks up s in the current and enclosing SymbolTables,
  // returns null if not found
  public AddressReference lookUpAddressReference(String s) {
    DataContainer d = lookUpDataFor(s);
    return (d != null) ? d.getAddressReference() : null;
  }

  // Looks up s in the current and enclosing SymbolTables,
  // returns null if not found
  public DataContainer lookUpDataFor(String s) {
    SymbolTable current = this;
    DataContainer result;
    while (current != null) {
      result = current.getDataContainer(s);
      if (result != null) {
        return result;
      }
      current = current.parent;
    }
    return null;
  }

  public void setAddressReference(String s, int a) {
    getDataContainer(s).setAddressReference(new AddressReference(a));
  }

  public DataContainer getDataContainer(String s) {
    return dictionary.get(s);
  }

  public void setParent(SymbolTable parent) {
    this.parent = parent;
  }
}
