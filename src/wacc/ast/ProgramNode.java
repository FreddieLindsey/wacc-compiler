package wacc.ast;

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
    boolean valid = true;

    for (FuncNode f : funcs) {
      valid &= f.isSemanticallyValid();
    }

    valid &= stat != null && stat.isSemanticallyValid();

    return valid;
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
