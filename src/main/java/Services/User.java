package Services;

import Exceptions.EmptyFieldException;
import Exceptions.IncorrectCredentialsException;
import Exceptions.UsernameNotAvailableException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;
import java.util.Iterator;


public class User {

    public static String user;

    public static String passw;

    public static String key = "Jar12345Jar12345";

    public static String Vector = "RandomInitVector";

    private static void checkIfFieldsAreEmptyPlayer(String username, String password) throws EmptyFieldException {
        if (username.isEmpty() | password.isEmpty())
            throw new EmptyFieldException();
    }

    private static void checkIfFieldsAreEmptyAdmin(String username, String password) throws EmptyFieldException {
        if (username.isEmpty() | password.isEmpty())
            throw new EmptyFieldException();
    }

    public static String encodePassword(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(1, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    static boolean correctAccount = false;

    public static String loginCheckPlayer(String username, String password) throws IncorrectCredentialsException {
        JSONParser parser = new JSONParser();
        Object p;
        JSONArray arrayPlayer = new JSONArray();
        try {
            FileReader readFile = new FileReader("src/main/resources/Player.json");
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if (p instanceof JSONArray) {
                arrayPlayer = (JSONArray) p;
            }
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Iterator<JSONObject> iterator = arrayPlayer.iterator();

        while (iterator.hasNext())
        {
            JSONObject obj = iterator.next();
            if (obj.get("Username:").equals(username)&& obj.get("Password:").equals(encodePassword(key, Vector, password)))
            {
                correctAccount = true;
                user = (String)obj.get("Username:");
                return "Player";
            }
        }
        return "";
    }

    public static String loginCheckAdmin(String username, String password)throws IncorrectCredentialsException {
        JSONParser parser = new JSONParser();
        Object p;
        JSONArray arrayAdmin = new JSONArray();
        try {
            FileReader readFile = new FileReader("src/main/resources/Admin.json");
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if (p instanceof JSONArray) {
                arrayAdmin = (JSONArray) p;
            }
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Iterator<JSONObject> iterator = arrayAdmin.iterator();

        while (iterator.hasNext())
        {
            JSONObject obj = iterator.next();
            if (obj.get("Username:").equals(username)&& obj.get("Password:").equals(encodePassword(key, Vector, password)))
            {
                correctAccount = true;
                user = (String)obj.get("Username:");
                return "Admin";
            }
        }
        return"";
    }

    public static void checkIncorrect() throws IncorrectCredentialsException {
        if(!correctAccount)
        {
            throw new IncorrectCredentialsException();
        }
    }


}





