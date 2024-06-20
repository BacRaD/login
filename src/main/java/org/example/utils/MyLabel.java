package org.example.utils;

import javax.swing.*;

public class MyLabel extends JLabel {

    public MyLabel(String string) {
        setText(string);
        setFont(Utils.myFont());
    }
}
