import javax.sound.midi.*;
import java.util.EventListener;

public class MiniMusicCmdLine implements ControllerEventListener {
    public static void main (String[]args){
        MiniMusicCmdLine mini = new MiniMusicCmdLine();
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
        try {
            int[] arrayofnumbers = new int[28];
            for (int g = 0; g < arrayofnumbers.length; g++) {
                arrayofnumbers[g] = (int) (Math.random() * 120);
            }


            Sequencer player = MidiSystem.getSequencer();
            player.open();
            Sequence seq = new Sequence(Sequence.PPQ, 4);

            int[] evetsIwant = {127};
            player.addControllerEventListener(this, evetsIwant);

            Track track = seq.createTrack();

            for (int i=5; i<120; i+=4) {
                track.add(makeEvent(144,1,i,100, i));
                track.add(makeEvent(176,1,127,0,i));
                track.add(makeEvent(128,1,i, 100, i+10));
            }
            player.setSequence(seq);
            player.setTempoInBPM(180);
            player.start();

        } catch ( Exception ex) {ex.printStackTrace();}
    }
    public void controlChange(ShortMessage event) {
        System.out.println("ля");
    }
}

