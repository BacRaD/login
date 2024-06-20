package org.example.utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyButton extends JButton {

    public MyButton(String text) {
        setText(text);
        setFont(Utils.myFont());
        setBackground(Color.WHITE);
        setSize(200, 30);
    }
}
