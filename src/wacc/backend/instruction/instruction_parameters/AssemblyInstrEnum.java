package wacc.backend.instruction.instruction_parameters;


public enum AssemblyInstrEnum {

  //BRANCHING

  B,  //use Label as argument!
  BL, //use Label as argument!

  // ARITHMETIC
  // <Operation>{<cond>}{S} Rd, Rn, Operand2

  ADD, // operand1 + operand2
  ADC, // operand1 + operand2 + carry
  SUB, // operand1 - operand2
  SBC, // operand1 - operand2 + carry - 1
  RSB, // operand2 - operand1
  RSC, // operand2 - operand1 + carry - 1

  //NOTE: Rd != Rm
  MUL, // Rd, Rm, Rs    // Rd = Rm * Rs
  MLA, // Rd, Rm, Rs,Rn // Rd = (Rm * Rs) + Rn

  // COMPARISON
  // <Operation>{<cond>} Rn, Operand2

  CMP, // operand1 - operand2, but result not written
  CMN, // operand1 + operand2, but result not written
  TST, // operand1 AND operand2, but result not written
  TEQ, // operand1 EOR operand2, but result not written

  // LOGIC OPERATIONS
  // <Operation>{<cond>}{S} Rd, Rn, Operand2

  AND, // operand1 AND operand2
  EOR, // operand1 EOR operand2
  ORR, // operand1 OR operand2
  ORN, // operand1 NOR operand2
  BIC, // operand1 AND NOT operand2 [ie bit clear]

  // DATA MOVEMENT
  // <Operation>{<cond>}{S} Rd, Operand2

  MOV, // operand2
  MVN, // NOT operand2
  LDR, // Load into register
  STR, // Store to memory

  //STACK

  PUSH, // Push value to stack NB: only R0=7 + LR
  POP   // Pop value from stack NB: only R0=7 + PC

}
