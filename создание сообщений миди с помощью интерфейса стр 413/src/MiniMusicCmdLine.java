import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class MiniMusicCmdLine implements ControllerEventListener  {
    drawit dr;
    public static void main (String[]args){
        MiniMusicCmdLine mini = new MiniMusicCmdLine();
      //  drawit dr = new drawit();
      //  dr.now();
        //   if (args.length < 2) {
        //       System.out.println("Не забудьте аргументы");
        //   }
        //   else {
        //       int instument = Integer.parseInt (args[0]);
        //       int note = Integer.parseInt(args[1]);
        //       mini.play(instument,note);
        //   }

        mini.play(1,1);
    }
    public static MidiEvent makeEvent (int cmd, int chl, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage first = new ShortMessage();
            first.setMessage(cmd, chl, one, two);
            event = new MidiEvent(first, tick);
        }
        catch (Exception e) {}
        return event;
    }

    public void play(int instrument, int note) {
        dr = new drawit();
        dr.now();
       // System.out.println(dr);
        //dr.OvalPaint2.repain();
        try {
            int[] arrayofnumbers = new int[28];
            for (int g = 0; g < arrayofnumbers.length; g++) {
                arrayofnumbers[g] = (int) (Math.random() * 80);
            }
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            Sequence seq = new Sequence(Sequence.PPQ, 4);

            int[] evetsIwant = {127};
            player.addControllerEventListener(this, evetsIwant);

            Track track = seq.createTrack();

            //   ShortMessage first = new ShortMessage();
            //   first.setMessage(192, 1, instrument, 0)
            //  MidiEvent chamgeInstrument = new MidiEvent(first, 1);
            //        track.add(makeEvent(192,1,104,0,1));

            //     ShortMessage c = new ShortMessage();
            //     c.setMessage(144, 1, note, 100);
            //    MidiEvent noteOn2 = new MidiEvent(c, 1);
            //    track.add(noteOn2);
            for (int i:arrayofnumbers) {
                //       track.add(makeEvent(195, 2,34,100, 15));
                //    ShortMessage b = new ShortMessage();
                //    b.setMessage(128, 1, note, 100);
                //    MidiEvent noteOff = new MidiEvent(b, 16);
                //     track.add(noteOff);
                track.add(makeEvent(144,1,i,100, i));
                track.add(makeEvent(176,1,127,0,i));
                track.add(makeEvent(128,1,i, 100, i+10));
            }
            player.setSequence(seq);
            player.setTempoInBPM(220);
            player.start();

        } catch ( Exception ex) {ex.printStackTrace();}
    }

    public void controlChange(ShortMessage event) {
       dr.FR();

    }
}

 class drawit extends JPanel {
     JFrame frame;
     OvalPaint2 OP;
     public  void FR(){
         OP.GD();
         frame.add(OP);
         frame.repaint();

     }
     class Button1L implements ActionListener {
         public void actionPerformed(ActionEvent event) {
         //    System.out.println(frame);
             //frame.repaint();

         }
     }
     public void now (){
         frame = new JFrame();
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(400, 400);
         JButton button1 = new JButton("change");
         frame.getContentPane().add(BorderLayout.SOUTH, button1);
         button1.addActionListener(new Button1L());
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
             g2d.fillOval((int) (Math.random()*400), (int) (Math.random()*400), (int) (Math.random()*100), (int) (Math.random()*100));

         }
     }
 }



