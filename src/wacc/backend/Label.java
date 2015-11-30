package wacc.backend;

import java.util.ArrayList;

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
  	return label;
  }


}
