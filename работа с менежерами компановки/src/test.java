import javax.swing.*;
import java.awt.*;

public class test {
    public static void main (String[]args){
        test te = new test();
        te.go();
    }
    public void go() {
        JFrame frame = new JFrame();
        JPanel jp1 = new JPanel();
        JButton gg = new JButton("gggffffffffffffffg");
        JButton gg2 = new JButton("ggggg");
        JButton gg3 = new JButton("ggggg");
        JLabel jl1 = new JLabel("22");
        JCheckBox jch1 = new JCheckBox("asd");;
        jch1.setSelected(true);
        jl1.setText("asd");
        jp1.add(jch1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        jp1.setBackground(Color.BLACK);
        jp1.setLayout(new BoxLayout(jp1, BoxLayout.Y_AXIS));
       // frame.getContentPane().add(BorderLayout.WEST, jp1);
        frame.add(jp1);
        frame.setVisible(true);




    }

}
