package wacc.backend.instruction;

import java.util.ArrayList;
import java.util.List;

public class InstructionBlock implements Instruction {

  private String label;
  private List<Instruction> instructions;

  public InstructionBlock() {
    instructions = new ArrayList<>();
  }

  public InstructionBlock(String label) {
    this();
    this.label = label;
  }

  public String toString() {
    StringBuilder code = new StringBuilder();

    if (label != null) {
      code.append(label);
      code.append(":\n");
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

  public int size() {
    int j = 0;
    for (Instruction i : instructions) {
      if (i instanceof InstructionBlock) {
        j += ((InstructionBlock) i).size();
      } else {
        j++;
      }
    }
    return j;
  }
}
