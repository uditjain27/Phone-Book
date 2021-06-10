package com.java.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Manager {
    static List<Contact> contacts;
    static {
        contacts=FileIO.read();
    }
    public static String add(String name, String mobile, String email, String category){
        if(contacts ==null){
            contacts = new ArrayList<>();
        }
        for (Contact c:contacts) {
            if(c.getMobile().equals(mobile))
                return "Contact Already Exists";
            if(c.getName().equals(name))
                return "Name Already Exists";
        }
        contacts.add(new Contact(name, mobile, email, category));
        FileIO.write();
        //Toast.makeText(this.clone(), "calling...", Toast.LENGTH_LONG).show();
        return "Contact Created" + "";
    }
    public static void delete(String mobile){
        contacts.remove(new Contact(null,mobile,null,null));
        FileIO.write();
    }
    public static void update(String oldMobi, String name, String mobile, String email, String category){
        delete(oldMobi);
        add(name, mobile, email, category);
        FileIO.write();
    }
    public static Contact getdetails(String oldnumber){
        for (Contact c:contacts) {
            if(c.getMobile().equals(oldnumber))
                 return c;
        }
        return null;
    }

    public static Contact getdetailsByName(String name){
        for (Contact c:contacts) {
            if(c.getName().equals(name))
                return c;
        }
        return null;
    }

    public static List<Contact> viewByName(String name){
        List<Contact> list=new ArrayList<>();
        for(Contact c:contacts){
            if(c.getName().startsWith(name))
                list.add(c);
        }
        return list;
    }
    public static List<Contact> viewByNumber(String mobile) {
        List<Contact> list = new ArrayList<>();
        for (Contact c : contacts) {
            if (c.getMobile().startsWith(mobile))
                list.add(c);
        }
        return list;
    }

    public static void sortByName(){
        if(contacts !=null)
            Collections.sort(contacts, (o1, o2) -> o1.getName().compareTo(o2.getName()));
    }

    public static void sortByNumber(){
        Collections.sort(contacts, (o1, o2) -> o1.getMobile().compareTo(o2.getMobile()));
    }
}