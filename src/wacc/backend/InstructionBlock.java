package wacc.backend;

import java.util.ArrayList;
import java.util.List;

public class InstructionBlock {

  private Label label;
  private List<Instruction> instructions;

  public InstructionBlock() {
    instructions = new ArrayList<>();
  }

  public InstructionBlock(Label label) {
    this();
    this.label = label;
  }

  public String toString() {
    StringBuilder code = new StringBuilder();

    if (label != null) {
      code.append(label.toString());
      code.append("\n");
    }

    for (Instruction i : instructions) {
      if (label != null) code.append("\t");
      code.append(i);
      code.append("\n");
    }

    return code.toString();
  }

  public void add(Instruction i) {
    instructions.add(i);
  }
}
