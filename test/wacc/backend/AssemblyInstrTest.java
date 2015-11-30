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

  @Test
  public void pushInstr() {
    args = new ArrayList<Arg>();

    arg1 = new Register(RegEnum.R0);
    args.add(arg1);

    instr = new AssemblyInstr(AssemblyInstrEnum.PUSH,
                       AssemblyInstrCond.NO_CODE, 
                       args);

    //System.err.println("pushInstr: " + instr.toString());
    //assertTrue(true);

    assertTrue(instr.toString().equals("PUSH {R0}"));
  }


}
