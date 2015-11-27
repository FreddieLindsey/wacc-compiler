package wacc.backend;

public class MemoryAccess extends Arg {

  private Register reg;
	
  public MemoryAccess(ArgEnum type, Register reg) {
    this.type = type;
    this.reg = reg;
  }

  public Register getReg() {
    return reg;
  }


}
