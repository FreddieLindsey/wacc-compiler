package wacc.ast;

import wacc.ast.assign.AssignNode;
import wacc.ast.type.TypeNode;

public class IdentNode extends AssignNode {

  private String ident;

  public IdentNode(String ident) {
    super();
    this.ident = ident;
  }

  public String getIdent() {
    return ident;
  }

  @Override
  public TypeNode type() {
    return symbolTable.lookUpType(ident);
  }

  @Override
  public boolean isSemanticallyValid() {
    if (ident.length() < 1) {
      return false;
    }
    if (!isFirstValid(ident.charAt(0))) {
      return false;
    }
    for (int i = 1; i < ident.length(); ++i) {
      if (!areRestValid(ident.charAt(i))) {
        return false;
      }
    }
    semanticallyValid = true;
    return semanticallyValid;
  }

  private boolean isFirstValid(char c) {
    return c == '_' || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
  }

  private boolean areRestValid(char c) {
    return (c >= '0' && c <= '9') || isFirstValid(c);
  }

  @Override
  public boolean validLeft() {
    return true;
  }

  @Override
  public boolean validRight() {
    return true;
  }
}
