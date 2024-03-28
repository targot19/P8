package com.example.javacalenderproject;

public class familyMember {

    // create attributes
    private String name;
    private String icon;
    private String color;

    // create constructor
    public familyMember(String name, String icon, String color) {
        this.name = name;
        this.icon = icon;
        this.color = color;
    }

    /** create methods for familyMember
     * with these methods, it is possible to retrieve the private attributes
     **/

    // @param memberName
    public void setName(String memberName) {
        this.name = memberName;
    }

    // @param memberIcon
    public void setIcon(String memberIcon) {
        this.icon = memberIcon;
    }

    // @param memberColor
    public void setColor(String memberColor) {
        this.color = memberColor;
    }
}
