package org.launchcode.questionquestin.models;

public enum EncounterType {

    STANDARD("standard"),
    BOSS("boss");

    private final String displayName;

    EncounterType(String displayName){
        this.displayName = displayName;

    }
}
