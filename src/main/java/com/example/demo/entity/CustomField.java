package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.NoArgsConstructor;

/**
 * https://www.cnblogs.com/noah-sheng/p/13252475.html
 * https://www.jianshu.com/p/a21f1633d79c
 * 刚开始在写代码时，由于不熟悉jackson，include属性选择的是JsonTypeInfo.As.PROPERTY,发现在对它的子类进行序列化的时候，property属性中的内容（这里是“type”）作为兄弟属性被序列化了一次。
 * 后来再查阅资料发现JsonTypeInfo.As.PROPERTY的意思是property中的属性作为数据的兄弟属性会被序列化一次，而JsonTypeInfo.As.EXITING_PROPERTY则是作为POJO中已经存在的属性被包含到序列化的结果中。
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = MultiSelectCustomField.class, name = MultiSelectCustomField.TYPE),
        @JsonSubTypes.Type(value = PickListCustomField.class, name = PickListCustomField.TYPE),
        @JsonSubTypes.Type(value = UrlCustomField.class, name = UrlCustomField.TYPE),
        @JsonSubTypes.Type(value = UUIDCustomField.class, name = UUIDCustomField.TYPE)
})
@NoArgsConstructor
public abstract class CustomField {
    private String name;
    private String value;

    public CustomField(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public abstract String getType();

    public String getValue() {
        return value;
    }
}
