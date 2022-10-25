package org.hbrs.se1.ws22.uebung3;

import java.util.List;

public class MemberView {

    private static Container c = Container.getInstance();




    public static void view(){
        List<Member> m = c.getCurrentList();
        for (int i = 0; i < c.size(); i++) {
            System.out.println(m.get(i));
        }


    }

    public static void main (String[] args) throws ContainerException {



        MemberView.view();

    }


}


