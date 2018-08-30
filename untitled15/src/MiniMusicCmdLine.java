import javax.sound.midi.*;
public class MiniMusicCmdLine {
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

            Sequencer player = MidiSystem.getSequencer();
            player.open();
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

         //   ShortMessage first = new ShortMessage();
         //   first.setMessage(192, 1, instrument, 0)
            //  MidiEvent chamgeInstrument = new MidiEvent(first, 1);
    //        track.add(makeEvent(192,1,104,0,1));

       //     ShortMessage c = new ShortMessage();
       //     c.setMessage(144, 1, note, 100);
        //    MidiEvent noteOn2 = new MidiEvent(c, 1);
        //    track.add(noteOn2);
            for (int i=5; i<120; i+=4) {
         //       track.add(makeEvent(195, 2,34,100, 15));
                    //    ShortMessage b = new ShortMessage();
                    //    b.setMessage(128, 1, note, 100);
                    //    MidiEvent noteOff = new MidiEvent(b, 16);
                    //     track.add(noteOff);
                track.add(makeEvent(144, 1,i,100, i));
                track.add(makeEvent(128, 1,i, 100, i+10));
            }
            player.setSequence(seq);
            player.start();
            System.exit(1);

        } catch ( Exception ex) {ex.printStackTrace();}
    }
}

