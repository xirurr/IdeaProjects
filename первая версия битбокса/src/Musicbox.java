import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

public class Musicbox implements Serializable {
    JFrame frame;
    JPanel buttons;
    JPanel instNames;
    JPanel flags;
    JPanel framePanel;
    ArrayList<JCheckBox> checkboxList;
    GridBagConstraints c;
    JButton startBT;
    JButton stopBT;
    JButton tempUPBT;
    JButton tempDWNBT;
    JLabel tempLBL;
    int[] instruments ={35,42,26,38,49,39,50,60,70,72,64,56,58,47,67,63};
    Sequencer player;
    int tempo;

    public static void main(String[] args){
        Musicbox mb = new Musicbox();
        mb.startit();
        mb.INFACE();
     //   mb.al();
    }
    void startit(){
        frame = new JFrame();
        framePanel = new JPanel();
        instNames = new JPanel();
        flags = new JPanel();
        buttons = new JPanel();
       // frame.setBackground(Color.blue);
        //flags.setBackground(Color.gray);
      //  buttons.setBackground(Color.gray);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        GridBagLayout gbl = new GridBagLayout();
        frame.setLayout(gbl);
        c = new GridBagConstraints();
        framePanel.add(BorderLayout.CENTER,flags);
        framePanel.add(BorderLayout.EAST,buttons);
        frame.setVisible(true);
    }
    void INFACE (){
        String [] listINST = {"bass drumm","Closed Hi-hat", "Open Hi-Hat", "Acoustic SNare", "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas","Whistle","Low Conga","Cowbell","Vibraslap","Low-mid Tom","Hight Agogo","Open Hi Conga"};
        checkboxList = new ArrayList<JCheckBox>();
        int i =0;
        int d =0;
        for (String g:listINST){
            c.gridx = d;
            c.gridy = i;
            JLabel var = new JLabel(g);
            frame.add(var,c);
            for (d=1; d<17; d++){
                c.gridx = d;
                c.gridy = i;
                JCheckBox var2 = new JCheckBox();
                var2.setSelected(false);
                frame.add(var2,c);
                checkboxList.add(var2);
            }
            d=0;
            i++;
        }
        startBT = new JButton("Start");
        c.gridx = 17;
        c.gridy = GridBagConstraints.RELATIVE;
        frame.add(startBT,c);
        stopBT = new JButton("Stop");
        frame.add(stopBT,c);
        tempUPBT = new JButton("Temp UP");
        frame.add(tempUPBT,c);
        tempDWNBT = new JButton("Temp DOWN");
        frame.add(tempDWNBT,c);
        startBT.addActionListener(new startBTL());
        stopBT.addActionListener(new stopBTL());
        tempUPBT.addActionListener(new tempUPBTL());
        tempDWNBT.addActionListener(new tempDWNBTL());
        tempLBL = new JLabel();
        tempLBL.setText(""+220);
        frame.add(tempLBL,c);
        frame.pack();
        }
        int[][] checkcheckBOX () {
        int[][] statofcheckbox = new int[16][16];
        for (int i = 0; i < 16; i++) {
            for (int a = 0; a < 16; a++) {
                int sum = (16*i)+a;
                JCheckBox var5 = checkboxList.get(sum);
                if (var5.isSelected()) {
                    statofcheckbox[i][a] = 1;
                }
            }
        }
        return  statofcheckbox;
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
    class startBTL implements ActionListener{
        public void actionPerformed (ActionEvent event){
            int[][] statofcheckbox = new int[16][16];
            statofcheckbox = checkcheckBOX();
            try {
                player = MidiSystem.getSequencer();
                player.open();
                Sequence seq = new Sequence(Sequence.PPQ, 4);
                Track track = seq.createTrack();
                track.add(makeEvent(192, 9, 1, 0, 0));

                for (int g = 0; g < 16; g++) {
                    for (int i = 0; i < 16; i++) {
                        if (statofcheckbox[g][i] == 1) {
                            track.add(makeEvent(144, 9, instruments[g], 100, i*10));
                            track.add(makeEvent(128, 9, instruments[g], 100, i*10+10));
                        }
                    }
                }
                player.setSequence(seq);
                if (tempo <= 20){
                    tempo = 220;
                }
                tempLBL.setText(""+tempo);
                frame.add(tempLBL,c);
                player.setTempoInBPM(tempo);
                player.start();

            }
            catch ( Exception ex) {ex.printStackTrace();}
        }
    }
    class stopBTL implements ActionListener{
        public void actionPerformed (ActionEvent event){
            player.stop();
        }
    }
    class tempUPBTL implements ActionListener{
        public void actionPerformed (ActionEvent event){
            if (tempo<=19){
                tempo=220;
            }
            tempo = tempo +20;
            tempLBL.setText(""+tempo);
            frame.add(tempLBL,c);

        }
    }
    class tempDWNBTL implements ActionListener{
        public void actionPerformed (ActionEvent event){
            if (tempo<=19){
                tempo=220;
            }
            tempo = tempo -20;
            tempLBL.setText(""+tempo);
            frame.add(tempLBL,c);
        }
    }
}

