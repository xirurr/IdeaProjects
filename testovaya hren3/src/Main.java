import java.util.*;

public class Main {
    public static void main (String [] args) {
        Scanner in = new Scanner(System.in);
        String var = in.nextLine();
        String[] varint = var.split(" ");
        System.out.println(Arrays.toString(varint));
        Arrays.sort(varint,
                (first, second)-> first.length() - second.length());
    //    System.out.println("макс длинна"+varint[varint.length-1]);
     //   System.out.println("мин длинна"+varint[0]);
     /*   int moderateleng = 0;
        for (String i:varint){
            moderateleng+=i.length();
            System.out.println(moderateleng);
        }
        System.out.println(moderateleng);
        moderateleng = moderateleng/(varint.length);
        System.out.println("средняя длинна"+moderateleng);
        for (String i:varint){
            if (i.length()>moderateleng)
                System.out.println(i);
        }*/

       /* Arrays.sort(varint,
                (first, second)-> (int)first.chars().distinct().count() - (int)second.chars().distinct().count());
    System.out.println(varint[0]);*/

      /*S2: for (String i:varint){
          S: for(int x=0;x<i.length()-1;x++){
               if (!(i.charAt(x)<i.charAt(x+1)))
               {
                   break S;
               }
               System.out.println(i);
               break S2;
           }
       }*/

     /*S3: for (String i:varint){
          if (i.chars().distinct().count() == i.length()){
              System.out.println(i);
              break S3;
          }
      }
*/

  S4:  for (String i:varint){
         int varl = i.length()/2;
         int vark=0;
         for (int x=0; x<=(varl-1); x++){
             if (!(i.charAt(x)==i.charAt((i.length()-1)-x))){
                 break;
             }
             vark++;
             if (vark==varl){
                 System.out.println(i);
                 break S4;
             }
         }
     }

    }
}