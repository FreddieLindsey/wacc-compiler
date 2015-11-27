package wacc.backend;

public class Register extends Arg {

  private RegEnum reg; 

  public Register(ArgEnum type, RegEnum reg) {
    this.type = type;
    this.reg = reg;
  }

  public RegEnum getReg() {
    return reg;
  }



}
