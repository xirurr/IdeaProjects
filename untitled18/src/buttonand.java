import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class buttonand {
    JFrame frame;
    OvalPaint2 OP;
    JButton button1;
    JButton button2;
    JLabel label;
    int x;
    int y;

    public static void main(String args[]) {
        buttonand ba = new buttonand();
        ba.go2();
    }
    public void go2() {
        label = new JLabel("ima");
        button1 = new JButton("change");
        button2 = new JButton("текст");
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.WEST, label);
        frame.getContentPane().add(BorderLayout.SOUTH, button1);
        button1.addActionListener(new Button1L());
        frame.getContentPane().add(BorderLayout.NORTH, button2);
        button2.addActionListener(new Button2L());
        frame.setSize(400, 300);
        frame.setVisible(true);
        OP = new OvalPaint2();
        OP.GD();
        frame.add(OP);

        for (int i = 0; i < 100; i++) {
            x++;
            y++;
            frame.repaint();
            try {
                Thread.sleep(50);
            } catch (Exception ex) {
                System.out.println("text");
            }
        }
    }
    class Button1L implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            frame.repaint();
        }
    }

    class Button2L implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            button2.setText("clicked");
        }
    }

    class OvalPaint2 extends JPanel {
        Color startColor;
        Color endColor;
       public void GD (){

           int red = (int) (Math.random() * 255);
           int green = (int) (Math.random() * 255);
           int blue = (int) (Math.random() * 255);
           startColor = new Color(red, green, blue);

           red = (int) (Math.random() * 255);
           green = (int) (Math.random() * 255);
           blue = (int) (Math.random() * 255);
           endColor = new Color(red, green, blue);
        }
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gradient = new GradientPaint(70, 70, startColor, 150, 150, endColor);
            g2d.setPaint(gradient);
            g2d.fillOval(x, y, 100, 100);
        }
    }
}


