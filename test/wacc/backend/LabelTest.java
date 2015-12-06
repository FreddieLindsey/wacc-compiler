package wacc.backend;

import org.junit.Test;
import wacc.backend.instruction.Label;

import static org.junit.Assert.*;

public class LabelTest {

  private Label l = new Label("a label");

  @Test
  public void returnsCorrectString() {
    assertEquals(l.toString(), "a label:");
  }
}