package Players;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.util.ArrayList;

public class Character {
    private String name;
    private ArrayList<String> features;

    public Character(String name, ArrayList<String> l) {
        this.name=name;
        features = l;
    }

    public void displayFeatures(){
        for(String f:features){
            //arata imaginea atribuita corespunzatoare feature ului
            //f.contains(
        }
    }
}
