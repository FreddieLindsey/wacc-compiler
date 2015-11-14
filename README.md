----------------------------
### Git Process
----------------------------

#### Rebase vs. Merge

Rebasing allows fitting multiple histories together, whereas merge takes the end result of the histories and merges the changes it sees. This is not good!

#### Updating from remote

Instead of using `git pull`, please first `git fetch` and then `git rebase <remote-name>/<remote-branch>` whilst checked out in the branch you are updating.

If you have made changes, it may be that you need to run `git push <remote-name> <remote-branch> -f` to force your changes up to the remote. **However** please be careful doing this as it does overwrite changes irreversibly.

#### After a pull request has been merged

To ensure any branches that are being worked on are up to date relative to the master, after a merge has been committed to master, it's really important to go through the following process:

- Checkout the master branch
- `git fetch`
- `git rebase origin/master`
- Checkout the branch to be updated
- `git rebase master` *or branch that this branch is based off (normally master)*

#### Merging to `master`

Merging to master should only be done through the `GitHub` interface. If you see a merge commit pop up, you've done something wrong.

**NEVER** work on `master`

----------------------------
### Testing
----------------------------

```bash
test
  -> wacc
    -> MainTest.java
```

The above explains the structure of the test suite for the project. All tests should be within the `test` directory, and then maintain the same directory structure as the `src` directory, appending `Test` to the name of the class it tests.

e.g. for a class `ASTNode` within a package `wacc.AST`, this would be the file structure:

```bash
test
  -> wacc
    -> AST
      -> ASTNodeTest.java
```

#### Adding tests

When adding new tests, using the file structure above, they should be added to the `Makefile` for the project, under the build `test`. For the example above, this means we append it to the build as so:

```
test:
  $(JUNIT) wacc.MainTest ... wacc.AST.ASTNodeTest
```

#### Test dependencies

The tests presume the use of standard JUnit 4 libraries. Simply import the libraries you wish to use in your test and the testing should work out of the box.

#### Reference class

```java
package wacc;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void test_test() {
        assertTrue(Main.test_test() == 0);
    }

}
```

tests the following class:

```java
package wacc;

...

public class Main {

    ...

    public static int test_test() {
      return 0;
    }
}
```

----------------------------
### Provided files/directories  
----------------------------

#### antlr

The antlr directory contains simple example ANTLR lexer and parser specification files BasicLexer.g4 and Basic.g4, along with a script antlrBuild that builds the corresponding Java class files using the ANTLR libraries (more details below).

#### lib

The lib directory contains the ANTLR library files in antlr-4.4-complete.jar.
You should not need to make any changes in this directory.

#### src

The src directory is currently empty (apart from a simple README file) and is
where we expect you to write your compiler code.

#### grun

The grun script allows you to run the ANTLR TestRig program that can assist you in debugging you lexer and parser (more details below).

#### compile

The compile script should be edited to provide a front-end interface to your WACC compiler. You are free to change the language used in this script, but do not change its name (more details below).

#### Makefile

Your Makefile should be edited so that running 'make' in the root directory
builds your WACC compiler. Currently running 'make' will call the antlrBuild
script and the attempt to compile all .java files within the src directory.
Depending on the structure of your code, you might not need to modify this file.

----------------------------
### Using the provided scripts
----------------------------

#### antlrBuild

This script takes a pair of ANTLR lexer and parser configuration files (set
within the script) and creates the corresponding Java classes that you can use
in your compiler. The Java files are written to the src/antlr directory and
should not be modified by hand. By default this script is set up to generate a
parse tree and a listerner pattern for traversing this tree, but you can modify
the compilation options within the script if you want to produce different
outputs

Important! - running the antlrBuild script will overwrite the antlr directory in
             the src directory. We heavily suggest you do not write any of your
             code within the src/antlr directory

#### grun

This script provides access to the ANTLR TestRig program. You will probably find
this helpful for testing your lexer and parser. The script is just a wrapper for
the TestRig in the project environment. You need to tell it what grammar to use
what rule to start parsing with and what kind of output you want.

For example:
  ./grun antlr.Basic prog -tokens
will run the TestRig using the 'Basic' grammar, starting from the rule fo 'prog'
and outputting the tokens seen by the lexer. To see how the parser groups these
tokens you can use the -tree or -gui options instead, such as:
  ./grun antlr.Basic prog -gui
In either case you will need to type in your input program and then close the
input stream with ctrl-D.

Rather than typing your input programs in by hand, you can pass the TestRig a
file to read by piping it in through stdin with
  ./grun antlr.Basic expr -gui < testfile
When using the TestRig in this way you won't need to hit ctrl-D to close the
input stream as the EOF character in the file does this for you.

#### compile

This script currently writes a TODO: message to the console, but you should
update it to call the the main class of your compiler with appropriate
arguments. Note that the lab's automated testing service will be using this
script as the access point to your compiler.

You will need to add the ANTLR jar file to the classpath of your calls to Java
if you want to be able to use any of the built in ANTLR features. You can do
this be setting the -cp option on the command line
  e.g.  java -cp bin:lib/antlr-4.4-complete.jar ...rest of call...
note that the bin: ensures that the bin directory is still part of your java
classpath.
