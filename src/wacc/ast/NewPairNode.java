package wacc.ast;

public class NewPairNode<F extends ExprNode, S extends ExprNode> extends PairNode<F, S> {

  public NewPairNode(ASTNode parent, F fst, S snd) {
    super(parent, fst, snd);
  }

}
