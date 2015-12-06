package wacc.backend.instruction;

import wacc.backend.instruction.Instruction;

public class DataMessage implements Instruction {

  private String name;

  public DataMessage(String name) {
    this.name = name;
  }

  public String toString() {
    return name;
  }

}
