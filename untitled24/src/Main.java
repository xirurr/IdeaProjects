import java.awt.*;
import javax.swing.*;

public class Main{
    public static void main(String[] args) {
        JFrame frame=new JFrame("Test");
        frame.setBounds(0, 0,400,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        JPanel contentPane = new JPanel(){
            Graphics2D g2;

            public void paintComponent(Graphics g){
                super.paintComponent(g);
                System.out.println("adsd");
                g2=(Graphics2D)g;
                g2.setColor(Color.BLACK);
                g2.drawLine((int)(Math.random()*20), (int)(Math.random()*20), (int)(Math.random()*360), (int)(Math.random()*20));
            }
        };
        frame.setContentPane(contentPane);
        for (int i=0; i<10; i++){
            frame.repaint();
            frame.repaint();
        }
    }
}