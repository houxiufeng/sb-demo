package com.example.demo.entity;

import com.example.demo.common.JacksonUtil;
import com.google.common.collect.Lists;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class MultiSelectCustomField extends CustomField{

    public static final String TYPE = "MultiSelect";
    private List<String> options;

    public MultiSelectCustomField(String name, String value, List<String> options) {
        super(name, value);
        this.options = options;
    }

    public List<String> getOptions() {
        return options;
    }

    public static void main(String[] args) {
        CustomField customField = new MultiSelectCustomField("kings","fitz", Lists.newArrayList("allen","fitz","elvis"));
        System.out.println(JacksonUtil.bean2Json(customField));
        CustomField pickListCustomField = new PickListCustomField("master", "allen");
        System.out.println(JacksonUtil.bean2Json(pickListCustomField));
        CustomField UrlCustomField = new UrlCustomField("master", "allen");
        System.out.println(JacksonUtil.bean2Json(UrlCustomField));
        CustomField uuidCustomField = new UUIDCustomField("master", "allen");
        System.out.println(JacksonUtil.bean2Json(uuidCustomField));
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
