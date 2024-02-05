package com.lattice.assignment.enums;

public enum Speciality {
    ORTHOPEDIC("Orthopedic"),
    GYNECOLOGY("Gynecology"),
    DERMATOLOGY("Dermatology"),
    ENT_SPECIALIST("ENT Specialist");

    private final String value;

    Speciality(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Speciality from(String value){
        if(value == null) return null;
        for(Speciality speciality: Speciality.values()){
            if(speciality.getValue().equalsIgnoreCase(value)) return speciality;
        }
        return null;
    }
}
