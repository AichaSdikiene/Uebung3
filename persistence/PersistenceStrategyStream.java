package org.hbrs.se1.ws22.uebung3.persistence;

import java.io.*;

import java.util.List;

public class PersistenceStrategyStream<Member> implements PersistenceStrategy<Member> {


    // URL of file, in which the objects are stored
    private String location = "objects.ser";
    private ObjectOutputStream objOut= null;
    private ObjectInputStream objIn = null;
    List<Member> newList= null;


    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save
     */


    @Override
    public void openConnection() throws PersistenceException {

    }

    @Override
    public void closeConnection() throws PersistenceException {

    }

    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Member> member) throws PersistenceException {
        if (location == (null)) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "No Connection");
        }
        try {

            this.objOut = new ObjectOutputStream(new FileOutputStream(location));

        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.FileNotFound, "File was not found");
        }

        try {
            objOut.writeObject(member);
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.InputError, e.getMessage());
        }
        try {
            objOut.close();

        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Connectiono is unavailable");
        }


    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<Member> load() throws PersistenceException {
        if (location == (null)) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "No Connection");
        }
        try {

            this.objIn = new ObjectInputStream(new FileInputStream(location));
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.FileNotFound, "File was not found");
        }

        try {

            newList = (List<Member>) objIn.readObject();
            objIn.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return newList;


    }

}

