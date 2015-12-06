package wacc.backend;

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
