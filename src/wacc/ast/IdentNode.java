package wacc.ast;

public class IdentNode implements ASTNode {

  private String ident;

  public IdentNode(String ident) {
    this.ident = ident;
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
    return true;
  }

  private boolean isFirstValid(char c) {
    return c == '_' || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
  }

  private boolean areRestValid(char c) {
    return (c >= '0' && c <= '9') || isFirstValid(c);
  }

}
