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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("=");
    if (this.type == ArgEnum.CONST) {
        sb.append(value);
    }
    return sb.toString();
  }

}
