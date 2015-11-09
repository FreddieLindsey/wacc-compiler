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
JUNIT := java org.junit.runner.JUnitCore

JFLAGS	:= -sourcepath $(SOURCE_DIR) -d $(OUTPUT_DIR) -cp lib/antlr-4.4-complete.jar

# the make rules

all: rules

# runs the antlr build script then attempts to compile all .java files within src
rules:
	cd $(ANTLR_DIR) && ./$(ANTLR)
	$(FIND) $(SOURCE_DIR) -name '*.java' > $@
	$(MKDIR) $(OUTPUT_DIR)
	$(JAVAC) $(JFLAGS) @$@
	$(RM) rules

test: rules
	$(JUNIT) wacc.MainTest

clean:
	$(RM) rules $(OUTPUT_DIR)
