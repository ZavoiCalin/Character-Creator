package services;

import controllers.AdminCharacterCreationController;
import controllers.LoginController;
import exceptions.*;
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

    // Tested
    public static String encodePassword(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(1, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            //System.out.println("Hello!"); // Adaugat acum
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    static boolean correctAccount = false;

    // Tested
    public static String loginCheckPlayer(String username, String password) {
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
                LoginController.usernam = (String)obj.get("Username:");
                return "Player";
            }
        }
        return "";
    }

    public static String checkPlayer(String username) {
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
            if (obj.get("Username:").equals(username))
            {
                correctAccount = true;
                LoginController.usernam = (String)obj.get("Username:");
                return "Player";
            }
        }
        return "";
    }

    // Tested
    public static String loginCheckAdmin(String username, String password) {
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
                LoginController.usernam= (String)obj.get("Username:");
                return "Admin";
            }
        }
        return"";
    }

    // Tested
    public static void checkIncorrect() throws IncorrectCredentialsException {
        if(!correctAccount)
        {
            throw new IncorrectCredentialsException();
        }
    }

    public static void addCharacter(String nameAvatar, String deletionKeyAvatar, String genderListObs, String earListObs, String eyeColorListObs, String hairstyleListObs) throws EmptyCharNameException, NameNotAvailableException, TooManyCharException {
        checkIfFieldsAreEmpty(nameAvatar, deletionKeyAvatar, genderListObs, earListObs, eyeColorListObs, hairstyleListObs);
        JSONObject obj = new JSONObject();
        JSONArray arrayPlayer = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        try {
            FileReader readFile = new FileReader("src/main/resources/Player.json");
            BufferedReader read = new BufferedReader(readFile);
            p = jp.parse(read);
            if (p instanceof JSONArray) {
                arrayPlayer = (JSONArray) p;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Iterator<JSONObject> iterator = arrayPlayer.iterator();
        JSONObject obj3=new JSONObject();
        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();
            if ((LoginController.usernam).equals(obj2.get("Username:")))
            {

                JSONArray avatars=new JSONArray();
                avatars=(JSONArray) obj2.get("Character:");
                if(avatars.size()==4)
                {
                    throw new TooManyCharException();
                }
                if(avatars.size() != 0) {
                    Iterator<JSONObject> iterator2 = avatars.iterator();
                    JSONObject obj4 = iterator2.next();
                    if ((nameAvatar.equals((String) obj4.get("Character name:")))) {
                        throw new NameNotAvailableException();
                    }
                }
                obj2.remove("Character:");
                obj3.put("Character name:", nameAvatar);
                obj3.put("Deletion key:", deletionKeyAvatar);
                obj3.put("Gender:", genderListObs);
                obj3.put("Ears:", earListObs);
                obj3.put("Eye color:", eyeColorListObs);
                obj3.put("Hairstyle:", hairstyleListObs);
                avatars.add(obj3);
                obj2.put("Character:", avatars);
                arrayPlayer.add(obj2);
                arrayPlayer.remove(obj2);
                break;
            }
        }


        try {
            File file = new File("src/main/resources/Player.json");
            FileWriter fisier = new FileWriter(file.getAbsoluteFile());
            fisier.write(arrayPlayer.toJSONString());
            fisier.flush();
            fisier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static JSONArray displayFeatures() {
        JSONArray arrayPlayer = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        try {
            FileReader readFile = new FileReader("src/main/resources/Player.json");
            BufferedReader read = new BufferedReader(readFile);
            p = jp.parse(read);
            if (p instanceof JSONArray) {
                arrayPlayer = (JSONArray) p;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject obj3 = new JSONObject();
        Iterator<JSONObject> iterator = arrayPlayer.iterator();
        JSONArray display = new JSONArray();
        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();
            if (obj2.get("Username:").equals(LoginController.usernam))
            {

                //takes the right vector that must be displayed
                display= (JSONArray) obj2.get("Character:");


            }
        }
        return display;

    }

    // Tested
    public static void checkIfFieldsAreEmpty(String nameAvatar, String deletionKeyAvatar, String genderListObs, String earListObs, String eyeColorListObs, String hairstyleListObs) throws EmptyCharNameException{
        if(nameAvatar.isEmpty() | deletionKeyAvatar.isEmpty() | genderListObs==null | earListObs==null | eyeColorListObs==null | hairstyleListObs==null)
            throw new EmptyCharNameException();

    }

    public static String deletionkey(String key) throws WrongDeletionKeyException {
        JSONObject obj = new JSONObject();
        String name=null;
        JSONArray arrayPlayer = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        try {
            FileReader readFile = new FileReader("src/main/resources/Player.json");
            BufferedReader read = new BufferedReader(readFile);
            p = jp.parse(read);
            if (p instanceof JSONArray) {
                arrayPlayer = (JSONArray) p;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Iterator<JSONObject> iterator = arrayPlayer.iterator();

        JSONObject obj3 = new JSONObject();
        while (iterator.hasNext()) {
            JSONObject obj2 = iterator.next();
            if ((LoginController.usernam).equals(obj2.get("Username:"))) {
                boolean b=false;
                JSONArray avatars = new JSONArray();
                avatars = ((JSONArray)obj2.get("Character:"));
                JSONObject obj4= new JSONObject();

                Iterator<JSONObject> iterator2 = avatars.iterator();
                while (iterator2.hasNext()) {
                    obj3 = iterator2.next();

                    if (obj3.get("Deletion key:").equals(key)) {
                        name=(String)obj3.get("Character name:");
                        obj4=obj3;
                        b=true;
                        break;



                    }
                }
                if(b==false)
                {
                    throw new WrongDeletionKeyException();
                }

                ((JSONArray)obj2.get("Character:")).remove(obj4);
                break;

            }
        }
        try {
            File file = new File("src/main/resources/Player.json");
            FileWriter fisier = new FileWriter(file.getAbsoluteFile());
            fisier.write(arrayPlayer.toJSONString());
            fisier.flush();
            fisier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return name;
    }

    public static String deletionkeyadmin(String key) throws WrongDeletionKeyException {
        JSONObject obj = new JSONObject();
        String name=null;
        JSONArray arrayPlayer = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        try {
            FileReader readFile = new FileReader("src/main/resources/Player.json");
            BufferedReader read = new BufferedReader(readFile);
            p = jp.parse(read);
            if (p instanceof JSONArray) {
                arrayPlayer = (JSONArray) p;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Iterator<JSONObject> iterator = arrayPlayer.iterator();

        JSONObject obj3 = new JSONObject();
        while (iterator.hasNext()) {
            JSONObject obj2 = iterator.next();
            if ((LoginController.usernam).equals(obj2.get("Username:"))) {
                boolean b=false;
                JSONArray avatars = new JSONArray();
                avatars = ((JSONArray)obj2.get("Character:"));
                JSONObject obj4= new JSONObject();

                Iterator<JSONObject> iterator2 = avatars.iterator();
                while (iterator2.hasNext()) {
                    obj3 = iterator2.next();

                    if (obj3.get("Character name:").equals(key)) {
                        name=(String)obj3.get("Character name:");
                        obj4=obj3;
                        b=true;
                        break;



                    }
                }
                if(b==false)
                {
                    throw new WrongDeletionKeyException();
                }

                ((JSONArray)obj2.get("Character:")).remove(obj4);
                break;

            }
        }
        try {
            File file = new File("src/main/resources/Player.json");
            FileWriter fisier = new FileWriter(file.getAbsoluteFile());
            fisier.write(arrayPlayer.toJSONString());
            fisier.flush();
            fisier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return name;
    }

}