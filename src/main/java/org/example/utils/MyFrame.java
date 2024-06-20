package org.example.utils;

import org.example.user.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyFrame extends JFrame {

    private MyLabel lblName, lblNEmail, lblAddress, lblAge, lblPassword, lblPassword2;
    private MyInput txtName, txtEmail, txtAddress, txtAge;
    private MyPasswordInput  txtPassword, txtPassword2;
    private MyButton btn, btn2;
    private JPanel panel;

    public MyFrame(Integer width, Integer height) {

        this.setTitle("Login");
        this.setSize(width, height);
        setLayout(new BorderLayout(100, 100));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Panel placholder = new Panel();
        add(placholder, BorderLayout.WEST);

        Panel placeholder2 = new Panel();
        add(placeholder2, BorderLayout.EAST);

        Panel placholder3 = new Panel();
        add(placholder3, BorderLayout.NORTH);

        Panel placeholder4 = new Panel();
        add(placeholder4, BorderLayout.SOUTH);

        this.panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(Utils.myColor());
        panel.setLayout(new GridLayout(7, 2));


        initComponents();
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);

        initEvents();
    }

    public void initComponents() {
        this.lblName = new MyLabel("Név: ");
        this.lblNEmail = new MyLabel("E-mail: ");
        this.lblAddress = new MyLabel("Cím: ");
        this.lblAge = new MyLabel("Kor: ");
        this.lblPassword = new MyLabel("Jelszó");
        this.lblPassword2 = new MyLabel("Jelszó újra");

        this.txtName = new MyInput();
        this.txtEmail = new MyInput();
        this.txtAddress = new MyInput();
        this.txtAge = new MyInput();
        this.txtPassword = new MyPasswordInput();
        this.txtPassword2 = new MyPasswordInput();

        this.btn = new MyButton("Ellenőrzés");
        this.btn2 = new MyButton("Kilépés");

        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblNEmail);
        panel.add(txtEmail);
        panel.add(lblAddress);
        panel.add(txtAddress);
        panel.add(lblAge);
        panel.add(txtAge);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(lblPassword2);
        panel.add(txtPassword2);
        panel.add(btn);
        panel.add(btn2);
    }

    public void initEvents() {
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!verifyName()) {
                    new MyDialog("Hiba!", "A teljes nevet meg kell adni!");
                } else if (!verifyEmail()) {
                    new MyDialog("Hiba!", "Valós emailt kell megadni!");
                } else if (verifyPassword()) {
                    new MyDialog("Hiba!", "Nem lehet 3 egyforma karakter egymás mellett!");
                }  else if (txtAge.getText().isEmpty()) {
                    new MyDialog("Hiba", "Add meg az életkorod!");
                } else if (txtAddress.getText().isEmpty()) {
                    new MyDialog("Hbia", "Add meg a lakcímed!");
                } else {
                    User.getInstance().setName(txtName.getText());
                    User.getInstance().setEmail(txtEmail.getText());
                    User.getInstance().setAddress(txtAddress.getText());
                    User.getInstance().setAge(txtAge.getText());
                    User.getInstance().setPassword(txtPassword.getText());
                    new MyDialog("Üdv!", User.getInstance().getName() + " sikeresen bejelentkezett!");
                }
            }
        });
    }

    public boolean verifyName() {
        Pattern pattern = Pattern.compile("^([\\w[^0-9].-]{2,})( )+([\\w[^0-9].-]{2,})$");
        Matcher matcher = pattern.matcher(txtName.getText());
        return matcher.matches();
    }

    /*
            Helyi rész (local part)
                Karakterek: Tartalmazhat alfanumerikus karaktereket (a-z, A-Z, 0-9), valamint speciális karaktereket, mint például a pont (.),
                aláhúzás (_), kötőjel (-), és plusz (+).
                Hossz: Maximum 64 karakter hosszú lehet.
                Pontok: A helyi rész tartalmazhat pontokat, de a pontok nem lehetnek az elején,
                a végén, és nem lehetnek egymás mellett közvetlenül.
                Speciális karakterek: Bizonyos speciális karakterek, mint például a +, -, _, és . is használhatók,
                 de nem lehetnek egymás után többször.
            Domain rész (domain part)
                Karakterek: Tartalmazhat alfanumerikus karaktereket és kötőjelet (-).
                Hossz: Maximum 255 karakter hosszú lehet.
                Formátum: A domain résznek érvényes domain névnek kell lennie,
                amely egy vagy több ponttal elválasztott címkéből áll (pl. example.com).
                Pontok: A címkék között csak egy pont lehet, és nem kezdődhet vagy végződhet ponttal.

     */
    public boolean verifyEmail() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9-+_][a-zA-Z0-9-+_.]{0,62}[a-zA-Z0-9-+_]@[a-zA-Z0-9][a-zA-Z0-9.]{1,253}[a-zA-Z0-9]$");
        Matcher matcher = pattern.matcher(txtEmail.getText());
        if (matcher.matches()) {
            Pattern pattern2 = Pattern.compile("^(?!.*([.-_+])\\1).*$");
            Matcher matcher2 = pattern2.matcher(txtEmail.getText());
            return matcher2.matches();
        } else {
            return false;
        }
    }

    public boolean verifyPassword() {
        String pass = Arrays.toString(txtPassword.getPassword());
        String pass2 = Arrays.toString((txtPassword2.getPassword()));

        if (pass.equals(pass2)) {
            Pattern pattern = Pattern.compile("^.*(.)\\1{2}.*$");
            Matcher matcher = pattern.matcher(pass);
            return matcher.matches();
        }
        return true;
    }
}
