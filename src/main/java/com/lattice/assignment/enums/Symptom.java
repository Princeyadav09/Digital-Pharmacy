package com.lattice.assignment.enums;

public enum Symptom {
    ARTHRITIS("Arthritis"),
    BACK_PAIN("Back Pain"),
    TISSUE_INJURIES("Tissue Injuries"),
    DYSMENORRHEA("Dysmenorrhea"),
    SKIN_INFECTION("Skin Infection"),
    SKIN_BURN("Skin Burn"),
    EAR_PAIN("Ear Pain");

    private final String value;

    Symptom(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Symptom from(String value){
        if(value == null) return null;
        for(Symptom symptom: Symptom.values()){
            if(symptom.getValue().equalsIgnoreCase(value)) return symptom;
        }
        return null;
    }
    }
