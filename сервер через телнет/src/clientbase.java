import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class clientbase {
    static Map cdbase = new HashMap<String,String>();
    static ArrayList<String > onlinelist = new ArrayList<>();

    void baseadd(String hash, String name){
        cdbase.put(hash,name);
    }

    synchronized  boolean checkauth(String hashpwd, String name){
        boolean auth = false;
        System.out.println(hashpwd+"   "+ name);
        if (!onlinelist.contains(name)) {
            if (cdbase.get(hashpwd).equals(name)) {
                auth = true;
                onlinelist.add(name);
                System.out.println(onlinelist);
            }
        }
        return auth;
    }
    synchronized void deauth (String name){
        onlinelist.remove(name);
        System.out.println(name+"  vyshel");
    }
}
