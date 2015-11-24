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
JUNIT_FLAGS := -cp $(OUTPUT_DIR):$(TEST_BIN):lib/antlr-4.4-complete.jar:lib/hamcrest-1.3.jar:lib/junit-4.jar
JUNIT := java $(JUNIT_FLAGS) org.junit.runner.JUnitCore

JFLAGS	:= -sourcepath $(SOURCE_DIR) -d $(OUTPUT_DIR) -cp lib/antlr-4.4-complete.jar
JFLAGS_TEST := -sourcepath $(TEST_SRC) -d $(TEST_BIN) -cp lib/antlr-4.4-complete.jar:$(OUTPUT_DIR):lib/junit-4.jar

# the make rules

all: rules compiler test_compile test

# runs the antlr build script then attempts to compile all .java files within src
rules:
	cd $(ANTLR_DIR) && ./$(ANTLR)
	$(RM) rules

compiler:
	$(FIND) $(SOURCE_DIR) -name '*.java' > $@
	$(MKDIR) $(OUTPUT_DIR)
	$(JAVAC) $(JFLAGS) @$@
	$(RM) compiler
	cd ..

test_compile:
	$(RM) $(TEST_BIN)/*
	$(FIND) $(TEST_SRC) -name '*.java' > $@
	$(MKDIR) $(TEST_BIN)
	$(JAVAC) $(JFLAGS_TEST) @$@
	$(RM) test_compile

%:
	$(JUNIT) $@

clean:
	$(RM) out rules compiler test_bin test_compile referenceCompilerOutput $(OUTPUT_DIR) antlr/*.java antlr/*.tokens antlr/*.class src/antlr/*

.PHONY: all rules test_compile test clean
