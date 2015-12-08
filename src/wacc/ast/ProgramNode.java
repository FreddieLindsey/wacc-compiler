package wacc.ast;

import wacc.ast.function.FuncNode;
import wacc.ast.io.StatNode;
import wacc.backend.instruction.*;
import wacc.backend.static_methods.CallableMethod;
import wacc.symbolTable.SymbolTable;

import java.util.*;

public class ProgramNode extends ASTNode {

  private List<FuncNode> funcs;
  private StatNode stat;
  public static List<StringDataMessage> messages = new ArrayList<>();
  public static Set<CallableMethod> static_methods_called = new HashSet<>();

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
      if (symbolTable.lookUpType(f.getIdent().getIdent()) != null) return false;
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

  public List<FuncNode> getFuncs() {
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
    InstructionBlock stat_code = stat.generateCode();

    if (messages.size() > 0 || static_methods_called.size() > 0) {
      i.add(new DataMessage(".data"));

      for (StringDataMessage m : messages) {
        i.add(m);
      }

      for (CallableMethod m : new TreeSet<>(static_methods_called)) {
        i.add(m.message());
      }
    }

    i.add(new DataMessage(".text")); // temporary
    i.add(new InformationDataMessage(".global", "main")); // set entry point

    for (FuncNode f : funcs) {
      i.add(f.generateCode());
    }

    i.add(generateMain(stat_code));

    for (CallableMethod m : new TreeSet<>(static_methods_called)) {
      i.add(m.generateCode());
    }

    return i;
  }

  private InstructionBlock generateMain(InstructionBlock stat) {
    InstructionBlock main = new InstructionBlock("main");

    ArrayList<Arg> pushArgs = new ArrayList<>();
    pushArgs.add(new Register(RegEnum.LR));
    main.add(new AssemblyInstr(AssemblyInstrEnum.PUSH, AssemblyInstrCond.NO_CODE, pushArgs));

    main.add(stat);

    ArrayList<Arg> loadArgs = new ArrayList<>();
    loadArgs.add(new Register(RegEnum.R0));
    loadArgs.add(new Const(0, false));
    main.add(new AssemblyInstr(AssemblyInstrEnum.LDR, AssemblyInstrCond.NO_CODE, loadArgs));

    ArrayList<Arg> popArgs = new ArrayList<Arg>();
    popArgs.add(new Register(RegEnum.PC));
    main.add(new AssemblyInstr(AssemblyInstrEnum.POP, AssemblyInstrCond.NO_CODE, popArgs));

    main.add(new DataMessage(".ltorg"));

    return main;
  }
}
