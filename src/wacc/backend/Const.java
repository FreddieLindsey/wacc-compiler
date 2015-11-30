package wacc.backend;

public class Const extends Arg {
	
  private int value;
  private String str;

  private boolean isInt;
  private boolean isHash; // in ADD etc #NUM is used...

  public Const(int value, boolean isHash) {
    this.type = ArgEnum.CONST;
    isInt = true;
    this.value = value;
    this.isHash = isHash;
  }

  public Const(String str) {
  	// for e.g. LDR r0, =msg_1
  	this.type = ArgEnum.CONST;
    isInt = true;
  	this.str = str;
  }

  public int getValue() {
    return value;
  }

  public String getString() {
    return str;
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();

    if (isHash) {
      sb.append("#");
    } else {
      sb.append("=");

      if (!isInt && str != null) {
        sb.append(str);
      } else if (isInt) {
        sb.append(value);
      } else {
        //error
        return null;
      }
    }

    return sb.toString();
  }

}
