package wacc.ast;

import wacc.ast.type.*;

public class PairNode<F extends ExprNode, S extends ExprNode> extends TypeNode {

  private F fst;
  private S snd;	

  private TypeNode type;	
  
  public PairNode(F fst, S snd) {
    super(fst.type(), snd.type());
    this.fst = fst;
    this.snd = snd;
    fst.setParent(this);
    snd.setParent(this);
  }

  public F getFst() {
    return this.fst;
  }

  public S getSnd() {
    return this.snd;
  }

  public TypeNode getType() {
    return this.type;
  }

  @Override
  public boolean isSemanticallyValid() {
    return fst.isSemanticallyValid() && snd.isSemanticallyValid();
  }

}
