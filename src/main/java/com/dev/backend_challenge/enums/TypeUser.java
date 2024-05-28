package com.dev.backend_challenge.enums;

public enum TypeUser {
    USER(0),
    ADMIN(1),
    ROOT(2);

    private Integer typeUser;

    TypeUser(Integer typeUser){
        this.typeUser = typeUser;
    }

    public Integer getTypeUser(){
        return this.typeUser;
    }
}
