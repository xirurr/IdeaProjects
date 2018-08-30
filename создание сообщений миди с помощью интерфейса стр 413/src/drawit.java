/*
import javax.swing.*;
import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;

 class drawit extends JPanel {
    JFrame frame;
    OvalPaint2 OP;
    JButton button1;
    public static void main(String[]args) {
        drawit DRAW = new drawit();
        DRAW.now();
    }
 //   public static void JPmain () {
 //       drawit DRAW = new drawit();
 //       DRAW.now();
  //  }
 class Button1L implements ActionListener {
     public void actionPerformed(ActionEvent event) {
     System.out.println(frame);
         frame.repaint();
     }
 }
    public void now (){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button1 = new JButton("change");
        frame.getContentPane().add(BorderLayout.SOUTH, button1);
        button1.addActionListener(new Button1L());
        frame.setSize(400, 400);
        frame.setVisible(true);
        OP = new OvalPaint2();
        OP.GD();
        frame.add(OP);
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
            int x = (int) (Math.random()*400);
            int y = (int) (Math.random()*400);
            g2d.fillOval(x, y, 100, 100);
        }
    }

}

*/
