import javax.swing.*;
import java.awt.*;

public class test {
    JFrame frame;
    JPanel buttons;
    JPanel instNames;
    JPanel flags;
    JPanel framePanel;
    public static void main(String[] args) {
        test b = new test();
        b.Bbox();
    }
     void Bbox(){
        frame = new JFrame();
         frame.setSize(700, 400);
         GridBagLayout gbl = new GridBagLayout();
         frame.setLayout(gbl);
         GridBagConstraints c = new GridBagConstraints();
         c.anchor = GridBagConstraints.WEST;
        /* c.fill   = GridBagConstraints.NONE;
         c.gridheight = 1;
         c.gridwidth  = GridBagConstraints.REMAINDER;
         c.gridx = GridBagConstraints.RELATIVE;
         c.gridy = GridBagConstraints.RELATIVE;
         c.insets = new Insets(40, 0, 0, 0);
         c.ipadx = 0;
         c.ipady = 0;
         c.weightx = 0.0;
         c.weighty = 0.0;*/
           for (int i=0; i<7;i++) {
               for (int g=0;g<7;g++) {
                   c.gridx = i;
                   c.gridy = g;
             //      frame.add(new JLabel("g"));
                   JLabel var = new JLabel("g"+g);
                   frame.add(var,c);


                   /*frame.add(new JCheckBox());
                   frame.add(new JCheckBox());*/
               }
               }
          //     else {
            //   frame.add(new JCheckBox());}

           frame.setVisible(true);
     }
}
