package wacc.backend.instruction;

import wacc.backend.static_methods.MethodResolver;

public class Label extends Arg implements Instruction {

  private String label;

  public Label(String label) {
    this.type = ArgEnum.LABEL;
    this.label = label;
    MethodResolver.resolver().addLabel(label);
  }

  @Override
  public String toString() {
    return label;
  }

}
