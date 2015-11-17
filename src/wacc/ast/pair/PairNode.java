package wacc.ast.pair;

import wacc.ast.AssignNode;
import wacc.ast.ExprNode;
import wacc.ast.type.TypeNode;

public class PairNode<F extends ExprNode, S extends ExprNode> extends AssignNode {

  private F fst;
  private S snd;	
  
  public PairNode(F fst, S snd) {
    super();
    this.fst = fst;
    this.snd = snd;
    this.type = new TypeNode(fst.type(), snd.type());
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
    return fst.isSemanticallyValid() && snd.isSemanticallyValid();
  }

  @Override
  public boolean validLeft() {
    return false;
  }

  @Override
  public boolean validRight() {
    return false;
  }
}
