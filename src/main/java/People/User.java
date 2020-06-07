package People;

import Exceptions.IncorrectCredentials;

import javax.naming.NameAlreadyBoundException;

public interface User {
    public void logIn() throws IncorrectCredentials;
    public void createCh(String name) throws NameAlreadyBoundException;
    public void deleteCh(String name);

}
