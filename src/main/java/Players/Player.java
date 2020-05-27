package Players;

import Exceptions.IncorrectCredentials;
import Exceptions.NameAlreadyTaken;
import People.Person;

import javax.naming.NameAlreadyBoundException;
import java.util.ArrayList;

public class Player implements Person {
    private String username;
    private String password;
    private ArrayList<Character> chars;

    public Player(String name, ArrayList<Character> chars, String password) {
        username = name;
        this.chars = null;
        this.password=password;
    }


    public void logIn(String username, String password) throws IncorrectCredentials {
        try{
            if( !this.username.equals(username) || !this.password.equals(password))
                throw new IncorrectCredentials();
            //treci la alta fereastra
        }catch(IncorrectCredentials e){
            //allert_box cu mesaj
        }
    }

    public void logIn() throws IncorrectCredentials {

    }

    public void createCh(String name) throws NameAlreadyTaken {
        try {
            Character ch=new Character();
            if(ch.getName().equals("c1"))
                return;
        }catch(NameAlreadyTaken e){

        }

    }

    public void deleteCh(String name) {

    }
}
