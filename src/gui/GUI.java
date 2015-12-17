package gui;

import wacc.ExitRequestException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GUI {

  private static final int WINDOW_WIDTH = 1000;
  private static final int WINDOW_HEIGHT = 600;

  public static void main(String[] args) {
    JFrame window = new JFrame("WACC Compiler");
    window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    window.setLocationRelativeTo(null);

    JPanel windowItems = new JPanel();

    final Dimension fieldSize = new Dimension(WINDOW_WIDTH / 2 - 40, WINDOW_HEIGHT - 80);

    final JScrollPane scrollInput = new JScrollPane();
    final JTextArea input = new GUITextArea("Please type your WACC here", fieldSize);
    scrollInput.add(input);

    final JScrollPane scrollOutput = new JScrollPane();
    final JTextArea output = new GUITextArea("Output will appear here", fieldSize);
    output.setEditable(false);
    scrollOutput.add(output);

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

  private static void compileButton(JTextArea input, JTextArea output) throws IOException {
    byte[] bytes = input.getText().getBytes();
    InputStream i = new ByteArrayInputStream(bytes);
    try {
      output.setText(wacc.Main.compile(i));
    } catch (ExitRequestException|IOException e) {
      output.setText(e.toString());
    }
  }

}
