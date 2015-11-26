package wacc.backend;


public class AssemblyInstr {


  private AssemblyInstrEnum instrType;
  private ArrayList<String> args;


  public AssemblyInstr(AssemblyInstrEnum instrType, 
                       ArrayList<String> args) {
    this.instrType = instrType;
    this.args = args;
  }

  public instrType() {
    return instrType;
  }

  public getArgs() {
    return args;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    //sb.append(x);

    return sb.toString();
  }


}