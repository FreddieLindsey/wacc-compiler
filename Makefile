# Sample Makefile for the WACC Compiler lab: edit this to build your own comiler
# Locations

ANTLR_DIR	:= antlr
SOURCE_DIR	:= src
OUTPUT_DIR	:= bin
TEST_SRC    := test
TEST_BIN    := test_bin

# Tools

ANTLR	:= antlrBuild
FIND	:= find
RM	:= rm -rf
MKDIR	:= mkdir -p
JAVA	:= java
JAVAC	:= javac
JUNIT_FLAGS := -cp bin:test_bin:lib/hamcrest-1.3.jar:lib/junit-4.jar
JUNIT := java $(JUNIT_FLAGS) org.junit.runner.JUnitCore

JFLAGS	:= -sourcepath $(SOURCE_DIR) -d $(OUTPUT_DIR) -cp lib/antlr-4.4-complete.jar
JFLAGS_TEST := -sourcepath $(TEST_SRC) -d $(TEST_BIN) -cp lib/antlr-4.4-complete.jar:bin:lib/junit-4.jar

# the make rules

all: test_compile test

# runs the antlr build script then attempts to compile all .java files within src
rules:
	cd $(ANTLR_DIR) && ./$(ANTLR)
	$(FIND) $(SOURCE_DIR) -name '*.java' > $@
	$(MKDIR) $(OUTPUT_DIR)
	$(JAVAC) $(JFLAGS) @$@
	$(RM) rules
	cd ..

test_compile: rules
	$(RM) $(TEST_BIN)/*
	$(FIND) $(TEST_SRC) -name '*.java' > $@
	$(MKDIR) $(TEST_BIN)
	$(JAVAC) $(JFLAGS_TEST) @$@
	$(RM) test_compile

test:
	$(JUNIT) wacc.MainTest \
wacc.symbolTable.SymbolTableTest \
wacc.ast.ProgramNodeTest \
wacc.ast.IdentNodeTest \
wacc.ast.TypeNodeTest \
wacc.ast.CharNodeTest \
wacc.ast.BoolNodeTest \
wacc.ast.IntNodeTest \
wacc.ast.UnOpNodeTest \
wacc.ast.ParamNodeTest \
wacc.ast.StringNodeTest \
wacc.ast.NewAssignNodeTest

clean:
	$(RM) rules test_bin test_compile $(OUTPUT_DIR) antlr/*.java antlr/*.tokens antlr/*.class src/antlr/*

.PHONY: all rules test_compile test clean
