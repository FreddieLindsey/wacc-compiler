package wacc.ast.pair;

import wacc.ast.ExprNode;
import wacc.ast.IdentNode;
import wacc.ast.assign.AssignNode;
import wacc.ast.type.PairTypeNode;
import wacc.ast.type.TypeNode;

public class PairNode<F extends ExprNode, S extends ExprNode> extends AssignNode {

  private F fst;
  private S snd;

  public PairNode(F fst, S snd) {
    super();
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

  @Override
  public TypeNode type() {
    TypeNode t1 = (fst instanceof IdentNode) ?
      symbolTable.lookUp(((IdentNode) fst).getIdent()) :
      fst.type();
    TypeNode t2 = (snd instanceof IdentNode) ?
      symbolTable.lookUp(((IdentNode) snd).getIdent()) :
      snd.type();
    return new PairTypeNode(t1, t2);
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
