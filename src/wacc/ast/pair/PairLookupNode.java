package wacc.ast.pair;

import wacc.ast.ExprNode;
import wacc.ast.IdentNode;
import wacc.ast.type.TypeNode;

public class PairLookupNode extends ExprNode {

  private final String ident;
  private final boolean fst;

  public PairLookupNode(String ident, boolean fst) {
    this.ident = ident;
    this.fst = fst;
  }

  @Override
  public TypeNode type() {
    TypeNode t = symbolTable.lookUp(ident);
    if (t == null) return null;
    return (fst) ?
      t.getLHS().getType() : t.getRHS().getType();
  }

  @Override
  public boolean isSemanticallyValid() {
    return symbolTable.lookUp(ident) != null;
  }
}
