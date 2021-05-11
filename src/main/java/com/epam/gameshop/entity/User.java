package com.epam.gameshop.entity;

import java.util.Date;
import java.util.List;

public class User  {

    private long id;
    private int money;
    private String login;
    private String password;
    private Date dateOfBirthday;
    private String eMail;
    private String name;
    private String sName;
    private Address address;
    private List<User> friends;
    private List<Game> ownedGames;
    private boolean admin;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(Date dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<Game> getOwnedGames() {
        return ownedGames;
    }

    public void setOwnedGames(List<Game> ownedGames) {
        this.ownedGames = ownedGames;
    }

    public boolean isAdmin() {
        return admin;
    }


    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(boolean admin){
        this.admin = admin;
    }

    public User(long id, Date dateOfBirthday, String eMail, String name, String sName){
        this.id = id;
        this.dateOfBirthday = dateOfBirthday;
        this.eMail = eMail;
        this.name = name;
        this.sName = sName;
    }

    public User(long id, Date dateOfBirthday, String eMail, String name, String sName,boolean admin){
        this.id = id;
        this.dateOfBirthday = dateOfBirthday;
        this.eMail = eMail;
        this.name = name;
        this.sName = sName;
        this.admin = admin;
    }

    public User(long id, Date dateOfBirthday, String eMail, String name, String sName,Address address){
        this.id = id;
        this.dateOfBirthday = dateOfBirthday;
        this.eMail = eMail;
        this.name = name;
        this.sName = sName;
        this.address = address;
    }


    public User(long id, String login, String password, Date dateOfBirthday, String eMail, String name, String sName,
                Address address, List<User> friends, List<Game> ownedGames, boolean admin , int money) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.dateOfBirthday = dateOfBirthday;
        this.eMail = eMail;
        this.name = name;
        this.sName = sName;
        this.address = address;
        this.friends = friends;
        this.ownedGames = ownedGames;
        this.admin = admin;
        this.money = money;
    }

}
