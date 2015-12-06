package wacc.backend;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class InstructionBlockTest {

  private InstructionBlock i = new InstructionBlock();
  private InstructionBlock i_withlabel = new InstructionBlock(new Label("Bob"));

  @Test
  public void validToStringForEmptyInstructionBlock() {
    assertEquals(i.toString(), "");
  }

  @Test
  public void validToStingForJustLabel() {
    assertEquals(i_withlabel, "Bob:\n");
  }

  @Test
  public void validToStringForLabelWithInstruction() throws Exception {
    Instruction i1 = new AssemblyInstr(AssemblyInstrEnum.B, AssemblyInstrCond.NO_CODE, new ArrayList<Arg>());
    Instruction i2 = new AssemblyInstr(AssemblyInstrEnum.B, AssemblyInstrCond.NO_CODE, new ArrayList<Arg>());

    i_withlabel.add(i1);
    i_withlabel.add(i2);

    String expectedOutput = "Bob:\n\tB \n\tB \n";

    assertEquals(i_withlabel.toString(), expectedOutput);
  }
}