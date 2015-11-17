package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class PairNode<F extends ExprNode, S extends ExprNode> extends TypeNode {

  private F fst;
  private S snd;		
  
  public PairNode(F fst, S snd) {
    super(TypeEnum.PAIR);
    this.fst = fst;
    this.snd = snd;
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

}
