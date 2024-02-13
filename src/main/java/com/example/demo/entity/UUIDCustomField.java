package com.example.demo.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UUIDCustomField extends CustomField{

    public static final String TYPE = "UUID";

    public UUIDCustomField(String name, String value) {
        super(name, value);
    }

    @Override
    public String getType() {
        return TYPE;
    }

}
