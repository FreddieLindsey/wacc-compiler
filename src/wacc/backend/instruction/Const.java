package wacc.backend.instruction;

public class Const extends Arg {

  private int value;
  private String str;
  private char c;

  private boolean isInt;
  private boolean isHash; // in ADD etc #NUM is used...
  private boolean isCommand;

  public Const(int value, boolean isHash) {
    this.type = ArgEnum.CONST;
    isInt = true;
    this.value = value;
    this.isHash = isHash;
    this.isCommand = false;
  }

  public Const(String str, boolean isCommand) {
    this.type = ArgEnum.CONST;
    isInt = true;
    isHash = false;
    this.str = str;
    this.isCommand = isCommand;
  }

  public Const(char c) {
    this.type = ArgEnum.CONST;
    isInt = false;
    isHash = true;
    this.c = c;
    this.isCommand = isCommand;
  }

  public int getValue() {
    return value;
  }

  public String getString() {
    return str;
  }

  public char getChar() {
    return c;
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();

    if (isHash) {
      sb.append("#");
    } else {
      if (!isCommand) sb.append("=");
    }

    if (!isInt || str != null) {
      sb.append(str);
    } else if (isInt) {
      sb.append(value);
    } else {
      //error
      return null;
    }

    return sb.toString();
  }

}
