package com.codeXie.pojo;

import java.io.Serializable;

public class Position implements Serializable {
    private int posid;
    private String pname;
    private String pdesc;

    public Position(int posid, String pname, String pdesc) {
        this.posid = posid;
        this.pname = pname;
        this.pdesc = pdesc;
    }

    public Position() {
    }

    public int getPosid() {
        return posid;
    }

    public String getPname() {
        return pname;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPosid(int posid) {
        this.posid = posid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    @Override
    public String toString() {
        return "Position{" +
                "posid=" + posid +
                ", pname='" + pname + '\'' +
                ", pdesc='" + pdesc + '\'' +
                '}';
    }
}
