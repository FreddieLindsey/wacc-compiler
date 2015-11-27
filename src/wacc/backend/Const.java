package wacc.backend;

public class Const extends Arg {
	
  private int value;

  public Const(ArgEnum type, int value) {
    this.type = type;
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}
