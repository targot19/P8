package com.example.javacalenderproject;

public class familyMember {

    // create attributes
    private string name;
    private string icon;
    private string color;

    // create constructor
    public familyMember(string memberName, string memberIcon, string memberColor) {
        this.name = memberName;
        this.icon = memberIcon;
        this.color = memberColor;
    }

    /** create methods for familyMember
     * with these methods, it is possible to retrieve the private attributes
     **/

    // @param memberName
    public void setName(string memberName) {
        this.name = memberName;
    }

    // @param memberIcon
    public void setIcon(string memberIcon) {
        this.icon = memberIcon;
    }

    // @param memberColor
    public void setColor(string memberColor) {
        this.color = memberColor;
    }
}
