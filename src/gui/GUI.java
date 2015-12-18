package gui;

import wacc.ExitRequestException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GUI {

  private static final int WINDOW_WIDTH = 1000;
  private static final int WINDOW_HEIGHT = 600;

  public static void main(String[] args) {
    JFrame window = new JFrame("WACC Compiler");
    window.setBounds(10, 10, 40, 40);
    window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    window.setLocationRelativeTo(null);

    JPanel windowItems = new JPanel();

    final Dimension fieldSize = new Dimension(WINDOW_WIDTH / 2 - 40, WINDOW_HEIGHT - 80);
    final JTextArea input = new GUITextArea("Please type your WACC here");
    final GUIScrollPane scrollInput = new GUIScrollPane(input, fieldSize);

    final JTextArea output = new GUITextArea("Output will appear here");
    output.setEditable(false);
    final GUIScrollPane scrollOutput = new GUIScrollPane(output, fieldSize);

    JButton compile = new JButton("Compile");
    compile.setPreferredSize(new Dimension(WINDOW_WIDTH - 20, 20));

    compile.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          compileButton(input, output);
        } catch (IOException e_) {
          output.setText(e_.toString());
          output.setCaretPosition(0);
        }
      }
    });

    windowItems.add(compile);
    windowItems.add(scrollInput);
    windowItems.add(scrollOutput);

    window.getContentPane().add(windowItems);

    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private static void compileButton(JTextArea input, JTextArea output) throws IOException {
    byte[] bytes = input.getText().getBytes();
    InputStream i = new ByteArrayInputStream(bytes);
    try {
      output.setText(wacc.Main.compile(i));
    } catch (ExitRequestException | IOException e) {
      output.setText(e.toString());
    }
    output.setCaretPosition(0);
  }

}
