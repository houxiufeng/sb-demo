package com.example.demo.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PickListCustomField extends CustomField {

    public static final String TYPE = "PickList";

    public PickListCustomField(String name, String value) {
        super(name, value);
    }

    @Override
    public String getType() {
        return TYPE;
    }

}
