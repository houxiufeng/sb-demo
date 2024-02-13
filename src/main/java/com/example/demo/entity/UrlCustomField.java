package com.example.demo.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UrlCustomField extends CustomField{

    public static final String TYPE = "URL";

    public UrlCustomField(String name, String value) {
        super(name, value);
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
