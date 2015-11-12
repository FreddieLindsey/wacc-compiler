package wacc.ast;

public class ParamNode implements ASTNode {

	private TypeNode type;
	private IdentNode ident;

	public ParamNode(TypeNode type, IdentNode ident) {
 		this.type = type;
    this.ident = ident;
	}

  public TypeNode getType() {
    return type;
  }

  public IdentNode getIdent() {
    return ident;
  }

	@Override
	public boolean isSemanticallyValid() {
    if (!type.isSemanticallyValid()) {
      return false;
    }

    if (!ident.isSemanticallyValid()) {
      return false;
    }

    // TODO: check ident isn't already in scope

    return false;
  }


}
