/*
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class MuzSort {
    private ArrayList<String> songlist = new ArrayList<>();
    private ArrayList<String> varsonglist = new ArrayList<>();
    private ArrayList<Song> songmassive = new ArrayList<>();
    private FileReader FR;
    private BufferedReader BR;
    public static void main(String[] args) {
        new MuzSort().start();
    }
    private  void start() {
         {
            try {
                 FR = new FileReader("songs.txt");
                 BR = new BufferedReader(FR);
                readArrayList();
                System.out.println(songlist);
                for (String var:songlist){
                    String var2[] = var.split("/");
                    Song var1 = new Song(var2[0],var2[1]);
                    varsonglist.add(var2[1]+"/"+var2[0]);
                //    var1.setSongName(var2[1]);
                //    var1.setSongAuthor(var2[0]);
                    songmassive.add(var1);
                }
                songlist.clear();
                songlist = varsonglist;
             //   System.out.println(songlist);
                songmassive.sort(Comparator.comparing(Song::getSongName));
                for (Song var:songmassive){
                    String var1 = var.getSongName();
                    String var2 = var.getSongAuthor();
                    System.out.print(var1+"/"+var2+", ");
                }


             //   songlist.sort(Comparator.naturalOrder());
            //    System.out.println(songlist);
               // FW = new FileWriter("songs.txt");
               // BW = new BufferedWriter(FW);

              //  for (String var:songlist){
              //      BW.write(var+"\r");
              //  }
              //  BW.flush();


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    private  void readArrayList() throws Exception{
        while (BR.ready()){
            songlist.add(BR.readLine());
        }
    }
}

class Song {
    private  String name;
    private   String author;
    Song(String var21, String var20){
        name = var21;
        author = var20;
    }

    void setSongName(String var){
        name = var;
    }
    void setSongAuthor(String var){
        author = var;
    }
    String getSongName(){
        return name;
    }
    String getSongAuthor(){
        return author;
    }
}

*/
