package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI {

  private static final int WINDOW_WIDTH = 400;
  private static final int WINDOW_HEIGHT = 600;

  public static void main(String[] args) {
    JFrame window = new JFrame("WACC Compiler");
    window.setSize(400, 600);

    JPanel windowItems = new JPanel();

    final JTextField input = new JTextField("Please type your WACC here", 40);

    final JTextField output = new JTextField("Output will appear here", 40);

    JButton compile = new JButton("Compile");
    compile.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          compileButton(input, output);
        } catch (IOException e_) {
          output.setText(e_.toString());
        }
      }
    });

    windowItems.add(compile);
    windowItems.add(input);
    windowItems.add(output);

    window.getContentPane().add(windowItems);

    window.setVisible(true);
  }

  private static void compileButton(JTextField input, JTextField output) throws IOException {
    output.setText(wacc.Main.compile(input.getText()));
  }

}
