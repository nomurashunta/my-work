package com.example.apiApp.domain.model;

public enum  EditStatus {

    ADD,
    DELETE;

    public String toString(){
        if (this == EditStatus.ADD) return "追加";
        else return "削除";
    }

}
