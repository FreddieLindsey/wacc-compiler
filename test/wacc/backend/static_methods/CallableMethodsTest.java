package wacc.backend.static_methods;

import org.junit.Test;
import wacc.backend.instruction.InstructionBlock;

import static org.junit.Assert.assertEquals;

public class CallableMethodsTest {

  @Test
  public void checkPrintString() {
    String expectedOutput = "p_print_string:\n" +
      "\tPUSH {lr}\n" +
      "\tLDR r1, [r0]\n" +
      "\tADD r2, r0, #4\n" +
      "\tLDR r0, =msg_1\n" +
      "\tADD r0, r0, #4\n" +
      "\tBL printf\n" +
      "\tMOV r0, #0\n" +
      "\tBL fflush\n" +
      "\tPOP {pc}\n";

    InstructionBlock p_print_string = CallableMethods.p_print_string("msg_1");

    assertEquals(expectedOutput, p_print_string.toString());
  }

  @Test
  public void checkReadInt() {
    String expectedOutput = "p_read_int:\n" +
      "\tPUSH {lr}\n" +
      "\tMOV r1, r0\n" +
      "\tLDR r0, =msg_3\n" +
      "\tADD r0, r0, #4\n" +
      "\tBL scanf\n" +
      "\tPOP {pc}\n";

    InstructionBlock p_read_int = CallableMethods.p_read_int("msg_3");

    assertEquals(expectedOutput, p_read_int.toString());
  }

  @Test
  public void checkPrintLnString() {
    String expectedOutput = "p_print_ln:\n" +
      "\tPUSH {lr}\n" +
      "\tLDR r0, =msg_2\n" +
      "\tADD r0, r0, #4\n" +
      "\tBL puts\n" +
      "\tMOV r0, #0\n" +
      "\tBL fflush\n" +
      "\tPOP {pc}\n";

    InstructionBlock p_print_string_ln = CallableMethods.p_print_string_ln("msg_2");

    assertEquals(expectedOutput, p_print_string_ln.toString());
  }

}