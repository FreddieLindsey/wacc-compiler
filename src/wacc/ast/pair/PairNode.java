package wacc.ast.pair;

<<<<<<< 13bee15090abd00d60efafe016aec785e7f780af:src/wacc/ast/PairNode.java
import wacc.ast.type.*;
=======
import wacc.ast.ASTNode;
import wacc.ast.ExprNode;
import wacc.ast.type.TypeNode;
import wacc.ast.type.TypeEnum;
>>>>>>> Slight refactor:src/wacc/ast/pair/PairNode.java

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
