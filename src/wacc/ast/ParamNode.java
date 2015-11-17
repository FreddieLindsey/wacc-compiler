package wacc.ast;

import wacc.symbolTable.SymbolTable;
import wacc.symbolTable.TypeEnum;

public class ParamNode extends ASTNode {

  private TypeEnum type;
  private IdentNode ident;
  private SymbolTable scope;

  public ParamNode(TypeEnum type, IdentNode ident) {
    super();
    this.type = type;
    this.ident = ident;
    ident.setParent(this);
  }

  public TypeEnum getType() {
    return type;
  }

  public IdentNode getIdent() {
    return ident;
  }

  public void setScope(SymbolTable st) {
    this.scope = st;
  }

  @Override
  public boolean isSemanticallyValid() {
    return (
          ident.isSemanticallyValid()
      &&  (scope == null
      ||  scope.lookUp(ident.getIdent()) == null));
  }


}
