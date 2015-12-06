package wacc.backend.instruction;

import wacc.backend.Arg;
import wacc.backend.ArgEnum;
import wacc.backend.instruction.Instruction;

public class Label extends Arg implements Instruction {

  private String label;

  public Label(String label) {
    this.type = ArgEnum.LABEL;
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  @Override
  public String toString() {
  	StringBuilder output = new StringBuilder(label);

    output.append(":");

    return output.toString();
  }

}
