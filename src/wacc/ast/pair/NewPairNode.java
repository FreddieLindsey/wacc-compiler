package wacc.ast.pair;

import wacc.ast.ExprNode;

public class NewPairNode<F extends ExprNode, S extends ExprNode> extends PairNode<F, S> {

  public NewPairNode(F fst, S snd) {
    super(fst, snd);
  }

}
