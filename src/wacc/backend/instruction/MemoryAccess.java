package wacc.backend.instruction;

import java.util.ArrayList;

public class MemoryAccess extends Arg {

  private ArrayList<Arg> args;
  private Register reg;

  public MemoryAccess(Register reg) {
    this.type = ArgEnum.MEMACC;
    this.reg = reg;
  }

  public MemoryAccess(ArrayList<Arg> args) {
    this.type = ArgEnum.MEMACC;
    this.args = args;
  }

  public Register getReg() {
    return reg;
  }

  public ArrayList<Arg> getArgs() {
    return args;
  }

  @Override
  public String toString() {
    return "[" + reg.toString() + "]";
  }


}
