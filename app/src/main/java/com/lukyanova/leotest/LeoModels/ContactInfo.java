package com.lukyanova.leotest.LeoModels;

public class ContactInfo {

    private int id_account;
    private int type;
    private String name;
    private String account;

    public ContactInfo(int id_account, int type, String name, String account){
        this.id_account = id_account;
        this.type = type;
        this.name = name;
        this.account = account;
    }

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}
