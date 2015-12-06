package wacc.backend.instruction;

public enum AssemblyInstrCond {

  EQ, // Z set (equal)
  NE, // Z clear (not equal)
  HS, CS, // C set (unsigned higher or same)
  LO, C, // C clear (unsigned lower)
  MI, // N set (negative)
  PL, // N clear (positive or zero)
  VS, // V set (overflow)
  VC, // V clear (no overflow)
  HI, // C set and Z clear (unsigned higher)
  LS, // C clear or Z (set unsigned lower or same)
  GE, // N set and V set, or N clear and V clear (>or =)
  LT, // N set and V clear, or N clear and V set (>)
  GT, // Z clear, and either N set and V set, or N clear and V set (>)
  LE, // Z set, or N set and V clear,or N clear and V set (<, or =)
  AL, // always
  NV, // reserved.

  NO_CODE // as code is optional we can also have no code!


}