import javax.sound.midi.*;

public class Seq {
    public static void main (String[] args) {
        try {
            Sequence player = MidiSystem.getSequencer();
            ((Sequencer) player).open();
            Sequence seq = new Sequence(2, 4);
            Track t = seq.createTrack();

            t.add(myMidiEvent1);
            player.setSequence(seq);
            player.start();
        }
        catch ( MidiUnavailableException exe) {
            System.out.println("loh");
        }

    }


}
