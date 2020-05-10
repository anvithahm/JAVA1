import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.*;

/*
<applet code="Calculator.class" width=240 height=330>
</applet>

*/
public class Calculator extends JApplet {

    int KeySize=60;
    String operator="";
    float FirstNum;
    float SecondNum;
    String PressedKey;
    boolean IsResultShown=false;
    public void init() {
        getContentPane().setLayout(new GridLayout(2, 1));
        JTextField lcd=new JTextField();
        lcd.setText("0");
        lcd.setSize(KeySize*4, 30);
        add(lcd);
        JPanel buttonPanel=new JPanel();
        buttonPanel.setSize(4*KeySize,5*KeySize);
        buttonPanel.setLayout(new GridLayout(5, 4));
        JButton btns[]=new JButton[21];
        String keytexts1[]={"C","/","*","-","+"};
        String keytexts2[]={"0",".","="};
        for(int i=1;i<=20;i++)
        {   
            btns[i]=new JButton();
            btns[i].setSize(KeySize, KeySize);
            btns[i].setFont(new Font("Arial", Font.BOLD, 16));
            buttonPanel.add(btns[i]);
            if(i<4)
                {
                btns[i].setText("");
                btns[i].setEnabled(false);
                }
            else if(i>4&&i%4!=0&&i<16)
                btns[i].setText(Integer.toString(i%4+3*((i/4)-1)));
            else if(i%4==0)
                btns[i].setText(keytexts1[(i/4)-1]);
            else
                btns[i].setText(keytexts2[(i%4)-1]);
            if(i>3)
            {
            btns[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PressedKey=((JButton)e.getSource()).getText();
                    if(PressedKey.equals("C"))
                        {
                        FirstNum=0;
                        SecondNum=0;
                        lcd.setText("0");
                        operator="";
                        }
                    else if(PressedKey.equals("/")||PressedKey.equals("*")||PressedKey.equals("-")||PressedKey.equals("+"))
                        {
                        operator=PressedKey;
                        FirstNum=Float.parseFloat(lcd.getText());
                        lcd.setText("0");
                        }
                    else if(PressedKey.equals("="))
                        {
                        IsResultShown=true;
                        SecondNum=Float.parseFloat(lcd.getText());
                        float result=0.0f;
                        if(operator.equals("+"))
                            result=FirstNum+SecondNum;
                        else if(operator.equals("-"))
                            result=FirstNum-SecondNum;
                        else if(operator.equals("*"))
                            result=FirstNum*SecondNum;
                        else if(operator.equals("/"))
                            result=FirstNum/SecondNum;
                        lcd.setText(Float.toString(result));
                        }
                    else if(PressedKey.equals("."))
                        {
                        if(IsResultShown)
                            {
                                lcd.setText("0");
                                IsResultShown=false;
                            }
                        if(lcd.getText().contains(".")==false)
                            lcd.setText(lcd.getText()+".");
                        }
                    else 
                        {
                        if(IsResultShown||lcd.getText().equals("0"))
                            {
                                lcd.setText("");
                                IsResultShown=false;
                            }
                        lcd.setText(lcd.getText()+PressedKey);
                        }
                }
            });
            }
        }
        add(buttonPanel);   
    }
}
