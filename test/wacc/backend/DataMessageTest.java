package wacc.backend;

import org.junit.Test;
import wacc.backend.instruction.DataMessage;

import static org.junit.Assert.assertEquals;

public class DataMessageTest {

  private String message = ".text";
  private DataMessage dataMessage = new DataMessage(message);

  @Test
  public void checkToString() {
    assertEquals(dataMessage.toString(), message);
  }

}