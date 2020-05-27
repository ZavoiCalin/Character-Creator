package Players;

import java.util.ArrayList;

public class Character {
    private String name;
    private ArrayList<String> features;

    public Character(String name, ArrayList<String> l) {
        this.name=name;
        features = l;
    }

    public Character() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void displayFeatures(){
        for(String f:features){
            //arata imaginea atribuita corespunzatoare feature ului
            //f.contains(
        }
    }
}
