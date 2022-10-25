package org.hbrs.se1.ws22.uebung3;

public class Client {

    public static void main (String[] args) throws ContainerException {
        Container co = Container.getInstance();
        Member a = new ActualMember(101);
        Member b = new ActualMember(102);
        Member c = new ActualMember(103);
        Member d = new ActualMember(104);
        Member e = new ActualMember(105);
        co.addMember(a);
        co.addMember(b);
        co.addMember(c);
        co.addMember(d);
        co.addMember(e);
        MemberView.view();

    }

}
