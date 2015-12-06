package wacc.backend;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataMessageTest {

  private String message = ".text";
  private DataMessage dataMessage = new DataMessage(message);

  @Test
  public void checkToString() {
    assertEquals(dataMessage.toString(), message);
  }

}