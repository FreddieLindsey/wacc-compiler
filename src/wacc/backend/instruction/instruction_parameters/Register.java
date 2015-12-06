package wacc.backend.instruction.instruction_parameters;

public class Register extends Arg {

  private RegEnum reg;

  public Register(RegEnum reg) {
    this.type = ArgEnum.REG;
    this.reg = reg;
  }

  public RegEnum getReg() {
    return reg;
  }

  @Override
  public String toString() {
    return reg.name().toLowerCase();
  }


}
