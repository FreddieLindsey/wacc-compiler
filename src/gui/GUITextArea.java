package gui;

import javax.swing.*;
import java.awt.*;

public class GUITextArea extends JTextArea {

  public GUITextArea(String text) {
    super(text);
    this.setTabSize(2);
    this.setLineWrap(true);
  }

}
