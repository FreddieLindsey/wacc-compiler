package wacc.ast.pair;

import wacc.ast.ASTNode;
import wacc.ast.ExprNode;
import wacc.ast.pair.PairNode;

public class NewPairNode<F extends ExprNode, S extends ExprNode> extends PairNode<F, S> {

  public NewPairNode(F fst, S snd) {
    super(fst, snd);
  }

}
