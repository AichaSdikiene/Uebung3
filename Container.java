package org.hbrs.se1.ws22.uebung3;

import org.hbrs.se1.ws22.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws22.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws22.uebung3.persistence.PersistenceStrategyStream;

import java.util.*;

public class Container {
    private PersistenceStrategy p = new PersistenceStrategyStream();
    private List<Member> content;
    private static Container c = new Container();


    private Container() {
        content = new ArrayList<Member>();
    }


    public static Container getInstance() {

        return c;
    }

    public void store() throws PersistenceException {

        try {

            if (p == null) {
                throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Strategie von außen gesetzt");
            }

            p.save(content);
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Not Implemented!");
        }
    }

    public void load() throws PersistenceException {

        content = p.load();

    }


    public void addMember(Member member) throws ContainerException {
        Integer m = member.getID();

        for (int i = 0; i < content.size(); i++) {
            Integer c = content.get(i).getID();
            if (c == m) {
                throw new ContainerException("Das Member-Objekt mit der ID " + c +
                        " ist bereits vorhanden!");
            }

        }
        content.add(member);
    }

    public String deleteMember(int id) {
        for (int i = 0; i < content.size(); i++) {
            Integer c = content.get(i).getID();
            if (c == id) {
                content.remove(i);
                return "Das Member-Objekt mit der ID " + id + " wurde gelöscht";
            }
        }
        return "Das Member-Objekt mit der ID " + id + " ist nicht verfuegbar";

    }


    public List<Member> getCurrentList() {
            return content;
        }


    public int size() {
        return content.size();
    }

    public void setStrategy(PersistenceStrategy p){

        this.p = p;
    }

}
