package wacc.backend;


public class BarrelShift extends Arg{
	

  private BarrelShiftEnum shift;
  private Arg shiftee;

  public BarrelShift(BarrelShiftEnum shift, Arg shiftee) {
    this.shift = shift;
    this.shiftee = shiftee;
  }

  public BarrelShiftEnum getShiftType() {
    return shift;
  }

  public Arg getShiftee() {
    return shiftee;
  }

  @Override
  public String toString() {
    return shift.name() + "#" + shiftee.toString();
  } 


}
