package org.hbrs.se1.ws22.uebung3.Test;

import org.hbrs.se1.ws22.uebung3.ActualMember;
import org.hbrs.se1.ws22.uebung3.Container;
import org.hbrs.se1.ws22.uebung3.ContainerException;
import org.hbrs.se1.ws22.uebung3.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;



import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    Container co = Container.getInstance();
    Member a = new ActualMember(1);
    Member b = new ActualMember(2);
    Member c = new ActualMember(3);
    Member d = new ActualMember(1);


    @org.junit.jupiter.api.Test
    @BeforeEach
    void addMember() {
        try {
            co.addMember(a);
            co.addMember(b);
            co.addMember(c);
            co.addMember(d);
        } catch (ContainerException e){
            assertEquals("Das Member-Objekt mit der ID 1 ist bereits vorhanden!",e.getMessage());
        }

        assertEquals(3,co.size());

    }

    @org.junit.jupiter.api.Test
    void deleteMember() {

        assertEquals("Das Member-Objekt mit der ID 1 wurde gelöscht", co.deleteMember(1));
        assertEquals("Das Member-Objekt mit der ID 50 ist nicht verfuegbar", co.deleteMember(50));
        assertEquals(2,co.size());

    }

    @org.junit.jupiter.api.Test
    void dump() {
        co.getCurrentList();
    }

    @org.junit.jupiter.api.Test
    void size() {
        assertEquals(3,co.size());
    }
}