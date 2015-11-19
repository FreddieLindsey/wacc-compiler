package wacc.ast;

import wacc.ast.function.FuncNode;
import wacc.symbolTable.SymbolTable;

import java.util.ArrayList;

public class ProgramNode extends ASTNode {

  private ArrayList<FuncNode> funcs;
  private StatNode stat;

  public ProgramNode() {
    super();
    this.funcs = new ArrayList<>();
    this.symbolTable = new SymbolTable();
  }

  @Override
  public boolean isSemanticallyValid() {
    // Check the program does something
    if (stat == null) return false;

    // Check the functions have different names
    for (FuncNode f : funcs) {
      if (symbolTable.lookUp(f.getIdent().getIdent()) != null) return false;
      symbolTable.add(f.getIdent().getIdent(), f.getType());
    }

    // Check the functions are valid
    for (FuncNode f : funcs) {
      if (!f.isSemanticallyValid()) return false;
    }

    // Check the stat is valid and doesn't have immediate return
    if (stat.hasReturn() != null || !stat.isSemanticallyValid()) return false;

    semanticallyValid = true;
    return semanticallyValid;
  }

  public void addFunc(FuncNode f) {
    funcs.add(f);
    f.setParent(this);
  }

  public ArrayList<FuncNode> getFuncs() {
    return funcs;
  }

  public void addStat(StatNode stat) {
    if (this.stat != null) {
      throw new IllegalArgumentException("You cannot overwrite the final stat of a program");
    }
    this.stat = stat;
    stat.setParent(this);
  }

  public StatNode getStat() {
    return stat;
  }
}
