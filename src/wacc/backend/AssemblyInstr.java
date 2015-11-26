package wacc.backend;

import java.util.ArrayList;


public class AssemblyInstr {


  private AssemblyInstrEnum instrType;
  private AssemblyInstrCond cond;
  private ArrayList<String> args;


  public AssemblyInstr(AssemblyInstrEnum instrType, 
                       AssemblyInstrCond cond, 
                       ArrayList<String> args) {
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

  public ArrayList<String> getArgs() {
    return args;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    //sb.append(x);

    return sb.toString();
  }


}