package wacc.ast;

import wacc.ast.function.FuncNode;
import wacc.ast.io.StatNode;
import wacc.backend.instruction.*;
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
    if (stat.returnType() != null || !stat.isSemanticallyValid()) return false;

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

  public InstructionBlock generateCode() {
    InstructionBlock i = new InstructionBlock();

    i.add(new DataMessage(".text")); // temporary
    i.add(new InformationDataMessage(".global", "main")); // set entry point

    for (FuncNode f : funcs) {
      i.add(f.generateCode());
    }

    i.add(new Label("main"));

    ArrayList<Arg> pushArgs = new ArrayList<>();
    pushArgs.add(new Register(RegEnum.LR));
    i.add(new AssemblyInstr(AssemblyInstrEnum.PUSH, AssemblyInstrCond.NO_CODE, pushArgs));

    i.add(stat.generateCode());

    ArrayList<Arg> loadArgs = new ArrayList<>();
    loadArgs.add(new Register(RegEnum.R0));
    loadArgs.add(new Const(0, false));
    i.add(new AssemblyInstr(AssemblyInstrEnum.LDR, AssemblyInstrCond.NO_CODE, loadArgs));

    ArrayList<Arg> popArgs = new ArrayList<Arg>();
    popArgs.add(new Register(RegEnum.PC));
    i.add(new AssemblyInstr(AssemblyInstrEnum.POP, AssemblyInstrCond.NO_CODE, popArgs));

    i.add(new DataMessage(".ltorg"));

    return i;
  }
}
