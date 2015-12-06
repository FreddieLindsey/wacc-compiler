package wacc.backend;

import org.junit.Test;
import wacc.backend.instruction.InformationDataMessage;

import static org.junit.Assert.assertEquals;

public class InformationDataMessageTest {

  private String message = ".text";
  private String information = "information";
  private InformationDataMessage dataMessage = new InformationDataMessage(message, information);

  @Test
  public void checkToString() {
    String expectedOutput = message + " " + information;

    assertEquals(dataMessage.toString(), expectedOutput);
  }

}