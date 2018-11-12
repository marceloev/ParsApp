package com.pars.system.impls;

public class ModelOptional<object> {

    private object object;

    public ModelOptional() {

    }

    public ModelOptional(object value) {
        this.set(value);
    }

    public Boolean contains() {
        return (this.get() != null);
    }

    public object get() {
        return this.object;
    }

    public void set(object value) {
        this.object = value;
    }
}
