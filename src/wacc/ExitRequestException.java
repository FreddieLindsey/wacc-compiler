package wacc;

public class ExitRequestException extends Exception {

  private final int exitCode;
  private final String input;

  public ExitRequestException(int exitCode, String input) {
    this.exitCode = exitCode;
    this.input = input;
  }

  public int getExitCode() {
    return exitCode;
  }

  public String toString() {
    StringBuilder o = new StringBuilder();

    if (exitCode == Main.SYNTAX_EXIT) {
      o.append("There were syntax errors in the supplied stream of input"
          + "\n------------------------------\n");
    }

    int error_count = 1;
    for (String error : input.split("\n")) {
      o.append("Error " + error_count + ":\t" + error + "\n");
      error_count++;
    }
    return o.toString();
  }
}
