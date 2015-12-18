package gui;

import javax.swing.*;
import java.awt.*;

public class GUITextArea extends JTextArea {

  public GUITextArea(String text, Dimension fieldSize) {
    super(text);
    this.setPreferredSize(fieldSize);
    this.setTabSize(2);
    this.setLineWrap(true);
  }

}
