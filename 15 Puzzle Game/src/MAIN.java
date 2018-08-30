import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.StreamSupport;

import static javax.swing.UIManager.get;

public class MAIN {
    ArrayList<Integer> vararaylist = new ArrayList<>();
    ArrayList<kvadratik> buttons = new ArrayList<>();
    JFrame frame;
    JPanel center;
    GridBagConstraints c;
    int[][] karta;
    int positions[] = new int[16];

    public static void main(String[] args) {
       new MAIN().start();
    }
    void start(){
        map();
        graph();
        enabler();
    }
    void map(){
        for (int i=1; i<16;i++){
            vararaylist.add(i);
        }
        Collections.shuffle(vararaylist);
        while (!vozmoshnoreshit()){
            Collections.shuffle(vararaylist);
        }
        //System.out.println(vararaylist);
    }

    boolean vozmoshnoreshit(){
        int smena = 0;
        for (int i=0; i<14;i++){
            if (vararaylist.get(i) > vararaylist.get(i+1)){
                smena++;
            }
        }
        System.out.println(smena);
        if (smena % 2 == 0){
            return true;
        }
        else return false;
    }



    void graph(){
        frame = new JFrame();
        center = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gbl = new GridBagLayout();
        center.setLayout(gbl);
        frame.add(center);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        int var=0;
        karta = new int[4][4];
        for (int g=0; g<4;g++){
            for (int g1=0;g1<4;g1++) {
                if (var<15) {
                    buttons.add(new kvadratik(vararaylist.get(var), g1, g, buttons.size()));
                    karta[g1][g]=vararaylist.get(var);
                    var++;
                }
            }
        }
        frame.setVisible(true);
        frame.pack();
     //   System.out.println(karta[3][3]);
    }
    void enabler(){
            int [] var = coordsofzero();
            int x0 = var[0];
            int y0 = var[1];
            for (kvadratik var1:buttons){
                if (var1.getposx()==x0+1&&var1.getposy()==y0
                        || var1.getposx()==x0-1&&var1.getposy()==y0
                        || var1.getposy()==y0+1&&var1.getposx()==x0
                        || var1.getposy()==y0-1&&var1.getposx()==x0)
                {
                    var1.bt.setEnabled(true);
                }

            }

    }
    void disabler(){
        for (kvadratik var:buttons){
            var.bt.setEnabled(false);
        }
    }
    int[] coordsofzero(){
        int[] var2 = new int[2];
        for (int y=0;y<4;y++){
            for (int x=0;x<4;x++){
                if (karta[x][y] ==0){
                    var2[0] = x;
                    var2[1] = y;
                }
            }
        }
        return var2;
    }
    void setzero(int posx, int posy){
        int[] changevar = coordsofzero();
        int var =karta[posx][posy];
        karta[changevar[0]][changevar[1]]=var;
        karta[posx][posy]=0;
    }

    void dvoinoivodinarniy(){
        for (int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                    positions[j*4+i] = karta[i][j];
            }
        }
    }
    void checkready(){

        for (int i=0;i<16;i++){
            int var = 1+i;
            System.out.println("сравниваем "+var+"  и " + positions[i]);
            if (1+i!=positions[i]){
                if (i==15) {
                    System.out.println(i);
                    disabler();
                    JOptionPane.showMessageDialog(frame,"МОЛОДЕЦ");
                }
                break;
            }
        }
    }

    class kvadratik{
        JButton bt = new JButton();
        kvadratik(int txt,int g1, int g, int pos){
            bt.setText(String.valueOf(txt));
            posx=g1;
            posy=g;
            c.gridx=posx;
            c.gridy=posy;
            center.add(bt,c);
            position = pos;
            text = txt;
            bt.setEnabled(false);
            bt.addActionListener(new game());
        }
        int position;
        int text;
        int posx;
        int posy;

        int getposx(){
            return posx;
        }
        int getposy(){
            return posy;
        }
        int getText(){
            return text;
        }
        void setposx(int x){
            posx=x;
        }
        void setposy(int y){
            posy=y;
        }

        void drawit(){
            c.gridx=posx;
            c.gridy=posy;
            center.add(bt,c);
        }
        int getPosition() {
            return position;
        }
    }
    class game implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (kvadratik var: buttons){
                if ((String.valueOf(var.getText())).equals(e.getActionCommand())){
                    int posy = var.getposy();
                    int posx = var.getposx();
                    center.remove(var.bt);
                    center.updateUI();
                    frame.repaint();
                    int var1[]= coordsofzero();
                    setzero(posx,posy);
                    var.setposx(var1[0]);
                    var.setposy(var1[1]);
                    var.drawit();
                    disabler();
                    enabler();
                    frame.pack();
                    dvoinoivodinarniy();
                    checkready();
                }
            }

        }
    }


}
