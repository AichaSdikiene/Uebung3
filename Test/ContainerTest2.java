package org.hbrs.se1.ws22.uebung3.Test;

import org.hbrs.se1.ws22.uebung3.*;
import org.hbrs.se1.ws22.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws22.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws22.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest2 {

    Container co = Container.getInstance();
    PersistenceStrategyStream pr = new PersistenceStrategyStream();
    Member a = new ActualMember(101);
    Member b = new ActualMember(102);
    Member c = new ActualMember(103);
    Member d = new ActualMember(104);


    @Test
    void store() {
        co.setStrategy(null);
        PersistenceException e = Assertions.assertThrows(PersistenceException.class, () -> co.store());
        assertEquals("Keine Strategie von außen gesetzt", e.getMessage());
        co.setStrategy(new PersistenceStrategyMongoDB<Member>());
        e = Assertions.assertThrows(PersistenceException.class, () -> co.store());
        assertEquals("Not Implemented!", e.getMessage());
        pr.setLocation(null);
        co.setStrategy(pr);
        e = Assertions.assertThrows(PersistenceException.class, () -> co.store());
        assertEquals("No Connection", e.getMessage());
        pr.setLocation("objects.ser");
        try {
            co.setStrategy(pr);
            co.addMember(a);
            co.addMember(b);
            co.addMember(c);
            co.addMember(d);
            assertEquals(4, co.size());
            co.store();
            co.deleteMember(101);
            co.deleteMember(102);
            co.deleteMember(103);
            co.deleteMember(104);
            assertEquals(0, co.size());

        } catch (ContainerException | PersistenceException ex) {
            ex.printStackTrace();
        }
        MemberView.view();
    }


    @Test
    void load() {

        try {
            assertEquals(0, co.size());
            co.load();
            assertEquals(4, co.size());
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        MemberView.view();


    }
}