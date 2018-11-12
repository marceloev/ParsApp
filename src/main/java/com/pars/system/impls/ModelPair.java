package com.pars.system.impls;

import lombok.Data;

@Data
public class ModelPair<Key, Value> {

    private final Key key;
    private final Value value;

    public ModelPair(Key key, Value value) {
        this.key = key;
        this.value = value;
    }
}
