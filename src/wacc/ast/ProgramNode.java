package wacc.ast;

import wacc.ast.function.FuncNode;
import wacc.ast.io.StatNode;
import wacc.backend.instruction.*;
import wacc.symbolTable.SymbolTable;
import wacc.backend.static_methods.*;

import java.util.ArrayList;
import java.lang.reflect.Method;

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

    MethodResolver resolver = MethodResolver.resolver();

    i.add(new DataMessage(".text")); // temporary
    i.add(new InformationDataMessage(".global", "main")); // set entry point

    for (FuncNode f : funcs) {
      i.add(f.generateCode());
    }

    InstructionBlock main = new InstructionBlock("main");

    ArrayList<Arg> pushArgs = new ArrayList<>();
    pushArgs.add(new Register(RegEnum.LR));
    main.add(new AssemblyInstr(AssemblyInstrEnum.PUSH, AssemblyInstrCond.NO_CODE, pushArgs));

    main.add(stat.generateCode());

    ArrayList<Arg> loadArgs = new ArrayList<>();
    loadArgs.add(new Register(RegEnum.R0));
    loadArgs.add(new Const(0, false));
    main.add(new AssemblyInstr(AssemblyInstrEnum.LDR, AssemblyInstrCond.NO_CODE, loadArgs));

    ArrayList<Arg> popArgs = new ArrayList<Arg>();
    popArgs.add(new Register(RegEnum.PC));
    main.add(new AssemblyInstr(AssemblyInstrEnum.POP, AssemblyInstrCond.NO_CODE, popArgs));

    main.add(new DataMessage(".ltorg"));

    i.add(main);

    ArrayList<InstructionBlock> callables = generateCallableMethods();
    for (InstructionBlock block : callables) {
      i.add(block);
    }

    return i;
  }

  private ArrayList<InstructionBlock> generateCallableMethods() {
    ArrayList<String> labels = MethodResolver.resolver().getLabels();
    ArrayList<String> added = new ArrayList<>();
    Method methods[] = CallableMethods.class.getMethods();
    ArrayList<InstructionBlock> code = new ArrayList<>();

    for (Method method : methods) {
      String mName = method.getName();

      if (added.contains(mName)) {
        continue;
      }

      if (labels.contains(mName)) {
        try {
          InstructionBlock block = (InstructionBlock)method.invoke("msg_1"); // NEEDS FIXING
          code.add(block);
        } catch (Exception ex) {
          System.err.println("Clever code is not so clever");
          System.err.println(ex.getMessage());
        }
      }
    }

    return code;
  }
  
}
