package org.hbrs.se1.ws22.uebung3;

public class ActualMember implements Member{

    private Integer id;

    public ActualMember(Integer id){
        this.id = id;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public String toString(){
        return "Member (ID = "+ id + ")";
    }
}
