package wacc.backend;

import java.util.ArrayList;


public class AssemblyInstr {


  private AssemblyInstrEnum instrType;
  private AssemblyInstrCond cond;
  private ArrayList<Register> args;
  private BarrelShift bs;


  public AssemblyInstr(AssemblyInstrEnum instrType, 
                       AssemblyInstrCond cond, 
                       ArrayList<Register> args,
                       BarrelShift bs) {
    this.instrType = instrType;
    this.cond = cond;
    this.args = args;
    this.bs = bs;
  }

  public AssemblyInstrEnum instrType() {
    return instrType;
  }

  public AssemblyInstrCond getCond() {
    return cond;
  }

  public ArrayList<Register> getArgs() {
    return args;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    //sb.append(x);

    return sb.toString();
  }


}