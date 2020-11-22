package com.chanu.simplefirebasecrud;

public class Student {
    private String id;
    private String name;
    private String Address;
    private Integer contact;

    public Student() {
    }

    public Student(String id, String name, String address, Integer contact) {
        this.id = id;
        this.name = name;
        Address = address;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }
}
