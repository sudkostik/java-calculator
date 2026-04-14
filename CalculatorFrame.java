
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorFrame {
    private JFrame frame;
    private JTextField display;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public CalculatorFrame() {
        frame = new JFrame("Hesap Makinesi");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

        // Rakam butonları
        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            final int digit = i;
            numberButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    display.setText(display.getText() + digit);
                }
            });
            buttonPanel.add(numberButtons[i]);
        }

        
        JButton addButton = new JButton("+");
        JButton subButton = new JButton("-");
        JButton mulButton = new JButton("*");
        JButton divButton = new JButton("/");
        JButton eqButton = new JButton("=");
        JButton clrButton = new JButton("C");

        JButton[] opButtons = { addButton, subButton, mulButton, divButton, eqButton, clrButton };

        for (JButton button : opButtons) {
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();
                    handleOperator(cmd);
                }
            });
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void handleOperator(String cmd) {
        if (cmd.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
        } else if (cmd.equals("=")) {
            try {
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            display.setText("Hata");
                            return;
                        }
                        break;
                }
                display.setText(String.valueOf(result));
            } catch (NumberFormatException e) {
                display.setText("Hata");
            }
        } else {
            try {
                num1 = Double.parseDouble(display.getText());
                operator = cmd.charAt(0);
                display.setText("");
            } catch (NumberFormatException e) {
                display.setText("Hata");
            }
        }
    }
}
