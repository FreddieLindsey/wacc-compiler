package wacc.backend;

import org.junit.Test;
import wacc.backend.instruction.AssemblyInstr;
import wacc.backend.instruction.Instruction;
import wacc.backend.instruction.InstructionBlock;
import wacc.backend.instruction.Label;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class InstructionBlockTest {

  private InstructionBlock i = new InstructionBlock();
  private InstructionBlock i_withlabel = new InstructionBlock(new Label("Bob"));
  private Instruction i1 = new AssemblyInstr(AssemblyInstrEnum.B, AssemblyInstrCond.NO_CODE, new ArrayList<Arg>());
  private Instruction i2 = new AssemblyInstr(AssemblyInstrEnum.B, AssemblyInstrCond.NO_CODE, new ArrayList<Arg>());

  @Test
  public void validToStringForEmptyInstructionBlock() {
    assertEquals(i.toString(), "");
  }

  @Test
  public void validToStingForJustLabel() {
    assertEquals(i_withlabel.toString(), "Bob:\n");
  }

  @Test
  public void validToStringForLabelWithInstruction() {
    i_withlabel.add(i1);
    i_withlabel.add(i2);

    String expectedOutput = "Bob:\n\tB \n\tB \n";

    assertEquals(i_withlabel.toString(), expectedOutput);
  }

  @Test
  public void validToStringForJustInstructions() {
    i.add(i1);
    i.add(i2);

    String expectedOutput = "B \nB \n";

    assertEquals(i.toString(), expectedOutput);
  }
}