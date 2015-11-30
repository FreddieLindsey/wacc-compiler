package wacc.backend;

import java.util.ArrayList;


public class AssemblyInstr {


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

  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(instrType.name());
    if (cond != AssemblyInstrCond.NO_CODE) {
      sb.append(cond.name() + " ");
    }

    for (int i = 0; i < args.size(); i++) {
      if (i == args.size()-1) {
        sb.append(args.get(i).toString());
      } else {
        sb.append(args.get(i).toString() + ", ");
      }
    }

    return sb.toString();
  }


}
