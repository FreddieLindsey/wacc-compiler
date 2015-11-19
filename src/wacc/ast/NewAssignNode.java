package wacc.ast;

import wacc.ast.function.CallNode;
import wacc.ast.pair.NewPairNode;
import wacc.ast.type.FuncTypeNode;
import wacc.ast.type.PairTypeNode;
import wacc.ast.type.TypeNode;

public class NewAssignNode extends StatNode {

  private TypeNode t;
  private IdentNode i;
  private AssignNode rhs;

  public NewAssignNode(TypeNode t, IdentNode i, AssignNode rhs) {
    super();
    this.t = t;
    this.i = i;
    this.rhs = rhs;
    i.setParent(this);
    rhs.setParent(this);
  }

  public TypeNode getType() {
    return t;
  }

  public IdentNode getIdent() {
    return i;
  }

  public AssignNode getRHS() {
    return rhs;
  }

  @Override
  public boolean isSemanticallyValid() {
    if (parent.getSymbolTable().lookUpHere(i.getIdent()) != null) return false;

    TypeNode returnType;
    if (rhs instanceof CallNode) {
      returnType = (
        (FuncTypeNode) symbolTable.lookUp(
          ((CallNode) rhs).getIdent().getIdent())
      ).getReturnType();
    } else if (rhs instanceof NewPairNode) {
      ExprNode fst = ((NewPairNode) rhs).getFst();
      ExprNode snd = ((NewPairNode) rhs).getSnd();
      TypeNode fstType, sndType;


      if (fst instanceof IdentNode) {
        TypeNode t = symbolTable.lookUp(((IdentNode) fst).getIdent());
        if (t instanceof PairTypeNode) {
          fstType = new PairTypeNode(null, null);
        } else {
          fstType = t;
        }
      } else {
        fstType = fst.type();
      }

      if (snd instanceof IdentNode) {
        TypeNode t = symbolTable.lookUp(((IdentNode) snd).getIdent());
        if (t instanceof PairTypeNode) {
          sndType = new PairTypeNode(null, null);
        } else {
          sndType = t;
        }
      } else {
        sndType = snd.type();
      }

      returnType = (
        new PairTypeNode(fstType, sndType));
    } else {
      returnType = rhs.type();
    }

    boolean valid = i.isSemanticallyValid()
      && rhs.validRight()
      && rhs.isSemanticallyValid()
      && t.equals(returnType);

    if (!valid) return false;


    addToScope(i.getIdent(), t);

    semanticallyValid = true;
    return semanticallyValid;
  }

}
