package com.samplemgt.downjacket.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "jacketattribute")
public class JacketAttribute {

    @Id
    public String id;

    @JsonProperty("attribute")
    @Field("attribute")
    public String attribute;

    @JsonProperty("index")
    @Field("index")
    public String index;
}
