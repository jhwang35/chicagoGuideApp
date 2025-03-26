package edu.uic.cs478.a2project3;

// class organizes all data, ie name and website
public class Location {

    private String name;
    private String siteUrl;

    public Location(String name, String url){
        this.name = name;
        this.siteUrl = url;
    }

    public String getName(){
        return name;
    }

    public String getSiteUrl(){
        return siteUrl;
    }

}
