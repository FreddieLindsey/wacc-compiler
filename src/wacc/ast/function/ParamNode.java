package wacc.ast.function;

import wacc.ast.ASTNode;
import wacc.ast.IdentNode;
import wacc.ast.type.TypeNode;
import wacc.symbolTable.SymbolTable;

public class ParamNode extends ASTNode {

  private TypeNode type;
  private IdentNode ident;
  private SymbolTable scope;

  public ParamNode(TypeNode type, IdentNode ident) {
    super();
    this.type = type;
    this.ident = ident;
    ident.setParent(this);
  }

  public TypeNode getType() {
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
