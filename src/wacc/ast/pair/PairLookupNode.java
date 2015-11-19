package wacc.ast.pair;

import wacc.ast.AssignNode;
import wacc.ast.ExprNode;
import wacc.ast.IdentNode;
import wacc.ast.type.PairTypeNode;
import wacc.ast.type.TypeNode;

public class PairLookupNode extends AssignNode {

  private final String ident;
  private final boolean fst;

  public PairLookupNode(String ident, boolean fst) {
    this.ident = ident;
    this.fst = fst;
  }

  @Override
  public TypeNode type() {
    TypeNode t = symbolTable.lookUp(ident);
    if (t == null || !(t instanceof PairTypeNode)) return null;
    PairTypeNode t_ = (PairTypeNode) t;
    return (fst) ?
      t_.getFst() : t_.getSnd();
  }

  @Override
  public boolean isSemanticallyValid() {
    semanticallyValid = symbolTable.lookUp(ident) != null;
    return semanticallyValid;
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
