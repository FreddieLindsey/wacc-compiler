package wacc.ast.pair;

import wacc.ast.AssignNode;
import wacc.ast.ExprNode;
import wacc.ast.type.PairTypeNode;

public class PairNode<F extends ExprNode, S extends ExprNode> extends AssignNode {

  private F fst;
  private S snd;

  public PairNode(F fst, S snd) {
    super();
    this.fst = fst;
    this.snd = snd;
    this.type = new PairTypeNode(fst.type(), snd.type());
    fst.setParent(this);
    snd.setParent(this);
  }

  public F getFst() {
    return this.fst;
  }

  public S getSnd() {
    return this.snd;
  }

  @Override
  public boolean isSemanticallyValid() {
    semanticallyValid = fst.isSemanticallyValid() && snd.isSemanticallyValid();
    return semanticallyValid;
  }

  @Override
  public boolean validLeft() {
    return !(this instanceof NewPairNode);
  }

  @Override
  public boolean validRight() {
    return (this instanceof NewPairNode);
  }
}
