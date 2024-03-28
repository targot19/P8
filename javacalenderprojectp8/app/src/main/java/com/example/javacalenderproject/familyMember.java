package com.example.javacalenderproject;

public class familyMember {

    // create attributes
    private String familyName;
    private String familyIcon;
    private String familyColor;

    // create constructor
    public familyMember(String memberName, String memberIcon, String memberColor) {
        this.familyName = memberName;
        this.familyIcon = memberIcon;
        this.familyColor = memberColor;
    }

    /** create methods for familyMember
     * with these methods, it is possible to retrieve the private attributes
     **/

    // @param memberName
    public void setName(String memberName) {
        this.familyName = memberName;
    }

    // @param memberIcon
    public void setIcon(String memberIcon) {
        this.familyIcon = memberIcon;
    }

    // @param memberColor
    public void setColor(String memberColor) {
        this.familyColor = memberColor;
    }
}
