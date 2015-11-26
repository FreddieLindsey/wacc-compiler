package wacc.backend;


public enum AssemblyInstrEnum {
	
  // ARITHMETIC
  // <Operation>{<cond>}{S} Rd, Rn, Operand2

  ADD, // operand1 + operand2
  ADC, // operand1 + operand2 + carry
  SUB, // operand1 - operand2
  SBC, // operand1 - operand2 + carry -1
  RSB, // operand2 - operand1
  RSC, // operand2 - operand1 + carry - 1

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
  BIC, // operand1 AND NOT operand2 [ie bit clear]

  // DATA MOVEMENT
  // <Operation>{<cond>}{S} Rd, Operand2

  MOV, //operand2
  MVN //NOT operand2

}