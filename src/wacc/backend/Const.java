package wacc.backend;

public class Const extends Arg {
	
  private int value;
  private String str;

  private boolean isInt();

  public Const(int value) {
    this.type = ArgEnum.CONST;
    isInt = true;
    this.value = value;
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

  	if (!isInt && str != nill) {
  		return "=" + str;
  	} else if (isInt) {
      return "=" + value;
    } else {
      //error
      return null;
    }
  }

}
