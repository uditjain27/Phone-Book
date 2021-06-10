package com.java.project;

import java.io.Serializable;

public class Contact implements Serializable {

    private final String name;
    private final String mobile;
    private final String email;
    private final String category;



    public Contact(String name, String mobile, String email, String category) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return mobile.equals(contact.mobile);
    }

}