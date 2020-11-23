package com.company;

public enum Role {
    ADMIN("Администратор"),
    MANAGER("Менеджер"),
    CLIENT("Клиент");
    public String value;

    Role(String value) {
        this.value = value;
    }
}
