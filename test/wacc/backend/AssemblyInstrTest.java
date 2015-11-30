package wacc.backend;

import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AssemblyInstrTest {

  private AssemblyInstr instr;

  private Arg arg1;
  private Arg arg2;
  private Arg arg3;

  private ArrayList<Arg> args;


  @Test
  public void assemInstrInit() {
    args = new ArrayList<Arg>();

    instr = new AssemblyInstr(AssemblyInstrEnum.B,
                       AssemblyInstrCond.NO_CODE, 
                       args);

    //System.err.println("assemInstrInit: " + instr.toString());
    //assertTrue(true);

    assertTrue(instr.toString().equals("B"));
  }


}
