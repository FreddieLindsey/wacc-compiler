package wacc.backend.instruction;

public class InformationDataMessage extends DataMessage {

  private String information;

  public InformationDataMessage(String name, String information) {
    super(name);
    this.information = information;
  }

  public String toString() {
    StringBuilder output = new StringBuilder(super.toString());

    output.append(" ");
    output.append(information);

    return output.toString();
  }
}
