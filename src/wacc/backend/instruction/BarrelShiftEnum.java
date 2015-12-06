package wacc.backend.instruction;

public enum BarrelShiftEnum {

  LSL, // Logical shift left
  LSR, // Logical shift right i.e. unsigned division by a power of 2.
  ASR, // Arithmetic shift right i.e. signed division by a power of 2.
  ROR, // Rotate right i.e. bit rotate with wrap-around.
  RRX  // 33-bit rotate with wrap-around through carry bit.


}
