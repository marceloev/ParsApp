package com.pars.system.db;

public enum Persistences {

    Web("Pars"),
    Local("Pars_Local");

    private final String value;

    Persistences(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
