


public class BarrelShift {
	

  private BarrelShiftEnum shift;
  private int amount;

  public BarrelShift(BarrelShiftEnum shift, int amount) {
    this.shift = shift;
    this.amount = amount;
  }

  public BarrelShiftEnum getShift() {
    return shift;
  }

  public int getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return shift.name() + "#" + amount;
  } 


}