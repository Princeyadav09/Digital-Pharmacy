package com.lattice.assignment.enums;

import lombok.Data;

public enum City {
    NOIDA("Noida"),
    DELHI("Delhi"),
    FARIDABAD("Faridabad");

    private final String value;

    City(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static City from(String value){
        if(value == null) return null;
        for(City city: City.values()){
            if(city.getValue().equalsIgnoreCase(value.trim())) return city;
        }
        return null;
    }
}
