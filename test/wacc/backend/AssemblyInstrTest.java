package wacc.backend;

import org.junit.Test;
import wacc.backend.instruction.AssemblyInstr;
import wacc.backend.instruction.Label;
import wacc.backend.instruction.instruction_parameters.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AssemblyInstrTest {

  private AssemblyInstr instr;

  private ArrayList<Arg> args = new ArrayList<>();


  @Test
  public void assemInstrInit() {
    args.add(new Label("link"));

    instr = new AssemblyInstr(AssemblyInstrEnum.B,
      AssemblyInstrCond.NO_CODE,
      args);

    assertTrue(instr.toString().equals("B link"));
  }

  @Test
  public void pushInstr() {
    args.add(new Register(RegEnum.R0));

    instr = new AssemblyInstr(AssemblyInstrEnum.PUSH,
      AssemblyInstrCond.NO_CODE,
      args);

    assertTrue(instr.toString().equals("PUSH {r0}"));
  }


}
