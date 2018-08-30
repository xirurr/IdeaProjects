import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

public class Musicbox implements Serializable {
    private JFrame frame;
    JPanel west;
    JPanel center;
    JTextArea jta;
    JTextField jtf;
    private ArrayList<JCheckBox> checkboxList;
    private GridBagConstraints c;
    JLabel tempLBL;
    private int[] instruments ={35,42,26,38,49,39,50,60,70,72,64,56,58,47,67,63};
    private String [] listINST = {"bass drumm","Closed Hi-hat", "Open Hi-Hat", "Acoustic SNare", "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas","Whistle","Low Conga","Cowbell","Vibraslap","Low-mid Tom","Hight Agogo","Open Hi Conga"};
    private Sequencer player;
    private int tempo=220;
    Socket chatSocket=null;;
    BufferedReader BR =null;;
    Boolean SavedState = false;
//
    InputStreamReader ISR =null;
    DataOutputStream DOS=null;
    static int poschat;

    public static void main(String[] args){
        Musicbox mb = new Musicbox();
        mb.INFACE();
        mb.chatconnect();
    }

    void INFACE (){
        frame = new JFrame();
        west = new JPanel();
        center = new JPanel();
        JPanel doublebuttons = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gbl = new GridBagLayout();
        west.setLayout(gbl);
        frame.add(BorderLayout.WEST,west);
        frame.add(BorderLayout.CENTER,center);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        doublebuttons.setLayout(new BoxLayout(doublebuttons,BoxLayout.X_AXIS));
        c = new GridBagConstraints();
        poschat=0;

        frame.setVisible(true);
        checkboxList = new ArrayList<JCheckBox>();
        int i =0;
        int d =0;
        for (String g:listINST){
            c.gridx = d;
            c.gridy = i;
            JLabel var = new JLabel(g);
            west.add(var,c);
            for (d=1; d<17; d++){
                c.gridx = d;
                c.gridy = i;
                JCheckBox var2 = new JCheckBox();
                var2.setSelected(false);
                west.add(var2,c);
                checkboxList.add(var2);
            }
            d=0;
            i++;
        }
        frame.pack();

        JButton startBT = new JButton("Start");
        center.add(startBT);
        JButton stopBT = new JButton("Stop");
        center.add(stopBT);
        JButton tempUPBT = new JButton("Temp UP");
        center.add(tempUPBT);
        JButton tempDWNBT = new JButton("Temp DOWN");
        center.add(tempDWNBT);
        JButton saveBT = new JButton("save");
        center.add(saveBT);
        JButton loadBT = new JButton("load");
        center.add(loadBT);
        tempLBL = new JLabel();
        tempLBL.setText(""+220);
        center.add(tempLBL);
        JButton sendBT = new JButton("send");
        center.add(doublebuttons);
        doublebuttons.add(sendBT);
        jta = new JTextArea(10,20);
        jtf = new JTextField();
        jtf.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==10) {
                    try {
                        connectsendtext();
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        doublebuttons.add(jtf);
        jta.setEditable(false);
        jta.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(jta);
        scrollPane.setAutoscrolls(false);
        center.add(scrollPane);

        startBT.addActionListener(new startBTL());
        stopBT.addActionListener(new stopBTL());
        tempUPBT.addActionListener(new tempUPBTL());
        tempDWNBT.addActionListener(new tempDWNBTL());
        saveBT.addActionListener(new saveBTL());
        loadBT.addActionListener(new loadBTL());
        sendBT.addActionListener(new sendBTL());
        frame.pack();



    }

    void chatlistener(){
        System.out.println("попытка запустить второй");
        Thread chatControl = new Thread(new incomreader());
        System.out.println("вроде создан");
        chatControl.start();
        System.out.println("вроде начат");
    }

    void repaintcheckbox(){
        int i =0;
        int d =0;
        for (int z=0;z<16;z++){
            String var6 = new String();
            var6 = listINST[z];
            c.gridx = d;
            c.gridy = i;
            JLabel var = new JLabel(var6);
            west.add(var,c);
            for (int r=0; r<16; r++){
                int sum2 =( 16*z)+r;
                c.gridx = ++d;
                c.gridy = i;
                JCheckBox var2 = checkboxList.get(sum2);
                west.add(var2,c);
                d++;
            }
            d=0;
            i++;
        }

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

    void chatconnect() {
        try {
        if (chatSocket ==null) {
            System.out.println("попытка подключиться");
                chatSocket = new Socket("127.0.0.1", 9000);
                DOS = new DataOutputStream(chatSocket.getOutputStream());
                ISR = new InputStreamReader(chatSocket.getInputStream());
                BR = new BufferedReader(ISR);
            chatlistener();
        }
        else{
                DOS = new DataOutputStream(chatSocket.getOutputStream());
                ISR = new InputStreamReader(chatSocket.getInputStream());
                BR = new BufferedReader(ISR);
            chatlistener();
        }
    }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void connectsendtext() throws  Exception{
        if (chatSocket ==null) {
            chatconnect();
            DOS.writeUTF(jtf.getText());
            jtf.setText("");
        }
        else {
            DOS.writeUTF(jtf.getText());
            jtf.setText("");
        }
    }

    void getTextWriteChatbox(String message) throws Exception{
        jta.setText("");
            int lenth = Integer.parseInt(message);
            System.out.println(lenth);
            for (int i=0; i<lenth;i++){
                message = BR.readLine();
                System.out.println(message);
                jta.append(message+"\n");
            }
            jta.setCaretPosition(0);
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
            frame.repaint();
        }
    }
    class tempDWNBTL implements ActionListener{
        public void actionPerformed (ActionEvent event){
            if (tempo<=19){
                tempo=220;
            }
            tempo = tempo -20;
            tempLBL.setText(""+tempo);
            frame.repaint();
        }
    }
    class saveBTL implements ActionListener{
        public void actionPerformed (ActionEvent event){
            try {
                FileOutputStream fs = new FileOutputStream("test.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fs);
                oos.writeObject(checkboxList);
                oos.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
//            SavedState = true;
        }
    }
    class loadBTL implements ActionListener {
        public void actionPerformed (ActionEvent event){
            try {
                FileInputStream fs = new FileInputStream("test.ser");
                ObjectInputStream ois = new ObjectInputStream(fs);
                checkboxList = (ArrayList) ois.readObject();
                west.removeAll();
                west.updateUI();
                repaintcheckbox();
                frame.repaint();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    class sendBTL implements ActionListener{
        public void actionPerformed (ActionEvent event) {
            try {
                connectsendtext();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    public class incomreader implements Runnable{
        public void run(){
            try {
                if (chatSocket == null) {
                    chatconnect();


                    varvorIncomreader();
                } else {

                    varvorIncomreader();
                }
                    }
                    catch(Exception ex){
                    ex.printStackTrace();
                }
            }

        private void varvorIncomreader() throws Exception {
            String fb;
            if (BR == null) {
                chatconnect();
                while ((fb = BR.readLine()) != null) {
                    getTextWriteChatbox(fb);

                }}
            else{

                while ((fb = BR.readLine()) != null) {
                    getTextWriteChatbox(fb);}
            }
        }
    }
    }
