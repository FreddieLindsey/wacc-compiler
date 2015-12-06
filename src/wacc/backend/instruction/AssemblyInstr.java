package wacc.backend.instruction;

import wacc.backend.*;

import java.util.ArrayList;

public class AssemblyInstr implements Instruction {

  private AssemblyInstrEnum instrType;
  private AssemblyInstrCond cond;
  private ArrayList<Arg> args;


  public AssemblyInstr(AssemblyInstrEnum instrType, 
                       AssemblyInstrCond cond, 
                       ArrayList<Arg> args) {
    this.instrType = instrType;
    this.cond = cond;
    this.args = args;
  }

  public AssemblyInstrEnum instrType() {
    return instrType;
  }

  public AssemblyInstrCond getCond() {
    return cond;
  }

  public ArrayList<Arg> getArgs() {
    return args;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(instrType.name());

    if (instrType == AssemblyInstrEnum.POP
     || instrType == AssemblyInstrEnum.PUSH) {
      sb.append(" {");
    } else {
      if (cond != AssemblyInstrCond.NO_CODE) {
        // I think push/pop cannot have conditions
        sb.append(cond.name());
      }
      sb.append(" ");
    }

    for (int i = 0; i < args.size(); i++) {
      if (i == args.size()-1) {
        sb.append(args.get(i).toString());
      } else {
        sb.append(args.get(i).toString() + ", ");
      }
    }

    if (instrType == AssemblyInstrEnum.POP
     || instrType == AssemblyInstrEnum.PUSH) {
      sb.append("}");
    }

    return sb.toString();
  }

}
