package wacc.ast.io;

import wacc.ast.ASTNode;
import wacc.ast.type.TypeNode;
import wacc.backend.instruction.Instruction;
import wacc.symbolTable.SymbolTable;

import java.util.ArrayList;

public abstract class StatNode extends ASTNode {

  public void setSymbolTable(SymbolTable symbolTable) {
    this.symbolTable = symbolTable;
  }

  public TypeNode returnType() {
    return null;
  }

  public boolean returns() {
    return false;
  }

  public abstract ArrayList<Instruction> generateCode();



}
