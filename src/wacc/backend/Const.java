package wacc.backend;

public class Const extends Arg {
	
  private int value;

  public Const(int value) {
    this.type = type;
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
  	return "=" + value;
  }

}
