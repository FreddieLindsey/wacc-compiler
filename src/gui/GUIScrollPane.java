package gui;

import javax.swing.*;
import java.awt.*;

public class GUIScrollPane extends JScrollPane {

  public GUIScrollPane(JTextArea area, Dimension fieldSize) {
    super(area);
    this.setPreferredSize(fieldSize);
    this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
  }
}
