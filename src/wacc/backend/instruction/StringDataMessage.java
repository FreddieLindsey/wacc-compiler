package wacc.backend.instruction;

public class StringDataMessage extends DataMessage {

  private String content;

  public StringDataMessage(int i, String content) {
    super(".msg_" + i);
    this.content = content;
  }

  public String toString() {
    StringBuilder s = new StringBuilder(super.toString());
    s.append(":\n\t");
    s.append(".word\t");
    s.append(content.length());
    s.append("\n\t");
    s.append(".ascii\t\"");
    s.append(content);
    s.append("\"\n");
    return s.toString();
  }
}
