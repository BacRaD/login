package org.example.utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyDialog extends JDialog {

    private MyButton btn;
    private MyLabel lbl;
    public MyDialog(String title, String message) {
        setTitle(title);
        setSize(600, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 30, 10, 30));

        this.lbl = new MyLabel(message);
        lbl.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(lbl, BorderLayout.CENTER);

        this.btn = new MyButton("OK");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(btn, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}
