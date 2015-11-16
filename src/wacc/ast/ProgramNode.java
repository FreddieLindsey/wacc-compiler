package wacc.ast;

import wacc.symbolTable.SymbolTable;

import java.util.ArrayList;

public class ProgramNode extends ASTNode {

  private ArrayList<FuncNode> funcs;
  private StatNode stat;

  public ProgramNode() {
    super(null);
    this.funcs = new ArrayList<>();
    this.symbolTable = new SymbolTable(null);
  }

  @Override
  public boolean isSemanticallyValid() {
    boolean valid = true;

    for (FuncNode f : funcs) {
      valid &= f.isSemanticallyValid();
    }

    valid &= stat != null && stat.isSemanticallyValid();

    return valid;
  }

  public void add(FuncNode f) {
    funcs.add(f);
  }

  public ArrayList<FuncNode> getFuncs() {
    return funcs;
  }

  public StatNode getStat() {
    return stat;
  }

  public void setStat(StatNode stat) {
    if (this.stat != null) {
      throw new IllegalArgumentException("You cannot overwrite the final stat of a program");
    }
    this.stat = stat;
  }
}
