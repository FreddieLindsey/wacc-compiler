package wacc.backend.instruction.instruction_parameters;

public class MemoryAccess extends Arg {

  private Register reg;

  public MemoryAccess(Register reg) {
    this.type = ArgEnum.MEMACC;
    this.reg = reg;
  }

  public Register getReg() {
    return reg;
  }

  @Override
  public String toString() {
    return "[" + reg.toString() + "]";
  }


}
