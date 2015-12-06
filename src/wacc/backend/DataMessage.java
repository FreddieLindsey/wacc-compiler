package wacc.backend;

public class DataMessage implements Instruction {

  private String name;

  public DataMessage(String name) {
    this.name = name;
  }

  public String toString() {
    return name;
  }

}
