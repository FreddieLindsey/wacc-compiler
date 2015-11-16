package wacc.ast.function;

import wacc.ast.ASTNode;
import wacc.ast.IdentNode;
import wacc.symbolTable.SymbolTable;
<<<<<<< 13bee15090abd00d60efafe016aec785e7f780af:src/wacc/ast/ParamNode.java
import wacc.ast.type.*;
=======
import wacc.ast.type.TypeEnum;
>>>>>>> Slight refactor:src/wacc/ast/function/ParamNode.java

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
