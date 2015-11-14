package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class NewPairNode implements AssignRHSNode {

  private ExprNode fst;
  private ExprNode snd;

  public NewPairNode(ExprNode fst, ExprNode snd) {
    this.fst = fst;
    this.snd = snd;
  }

  public ExprNode getFst() {
    return this.fst;
  }

  public ExprNode getSnd() {
    return this.snd;
  }

  @Override
  public TypeEnum type() {
    //symbol table?
    return TypeEnum.PAIR;
  }

  @Override
  public boolean isSemanticallyValid() {
    //don't need to know anything about types!
    return fst.isSemanticallyValid()
        && snd.isSemanticallyValid();
  }

}
