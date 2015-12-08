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
      if (!i.toString().equals("")) {
        boolean ident = label != null;
        if (i instanceof InstructionBlock) {
          code.append(((InstructionBlock) i).toStringBlock(ident));
        } else {
          if (ident) code.append("\t");
          code.append(i);
          code.append("\n");
        }
      }
    }

    return code.toString();
  }

  public String toStringBlock(boolean ident) {
    StringBuilder code = new StringBuilder(this.toString());
    StringBuilder out = new StringBuilder();
    List<String> strings = new ArrayList<>();
    while (code.indexOf("\n") > -1) {
      strings.add(code.substring(0, code.indexOf("\n")));
      code = new StringBuilder(code.substring(code.indexOf("\n") + 1, code.length()));
    }

    for (String s : strings) {
      if (ident) out.append("\t");
      out.append(s);
      out.append("\n");
    }

    return out.toString();
  }

  public void add(Instruction i) {
    instructions.add(i);
  }

  public void addAll(InstructionBlock i) {
    instructions.addAll(i.getInstructions());
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

  public List<Instruction> getInstructions() {
    return instructions;
  }
}
