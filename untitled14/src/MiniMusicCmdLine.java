import javax.sound.midi.*;
public class MiniMusicCmdLine {
    public static void main (String[]args){
        MiniMusicCmdLine mini = new MiniMusicCmdLine();
        if (args.length < 2) {
            System.out.println("Не забудьте аргументы");
        }
        else {
            int instument = Integer.parseInt (args[0]);
            int note = Integer.parseInt(args[1]);

            System.out.println("первый"+args[0]);
            System.out.println("второй"+args[1]);

            mini.play(instument,note);
        }
    }

    public void play(int instrument, int note) {
        try {
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrument, 0);
            MidiEvent chamgeInstrument = new MidiEvent(first, 1);
            track.add(chamgeInstrument);

            ShortMessage c = new ShortMessage();
            c.setMessage(144, 1, note, 100);
            MidiEvent noteOn2 = new MidiEvent(c, 1);
            track.add(noteOn2);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, note, 100);
            MidiEvent noteOff = new MidiEvent(b, 16);
            track.add(noteOff);
            player.setSequence(seq);
            player.start();
        } catch ( Exception ex) {
            ex.printStackTrace();


        }
    }
}