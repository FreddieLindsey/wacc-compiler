package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI {

  private static final int WINDOW_WIDTH = 600;
  private static final int WINDOW_HEIGHT = 600;

  public static void main(String[] args) {
    JFrame window = new JFrame("WACC Compiler");
    window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    window.setLocationRelativeTo(null);

    JPanel windowItems = new JPanel();


    final Dimension fieldSize = new Dimension(WINDOW_WIDTH / 2 - 40, WINDOW_HEIGHT - 80);
    final JTextField input = new JTextField("Please type your WACC here");
    input.setPreferredSize(fieldSize);

    final JTextField output = new JTextField("Output will appear here");
    output.setPreferredSize(fieldSize);

    JButton compile = new JButton("Compile");
    compile.setPreferredSize(new Dimension(WINDOW_WIDTH - 20, 20));

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
