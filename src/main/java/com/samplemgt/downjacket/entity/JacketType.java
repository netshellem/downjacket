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
@Document(collection = "jackettype")
public class JacketType {

    @Id
    public String id;

    @JsonProperty("type")
    @Field("type")
    public String type;

    @JsonProperty("index")
    @Field("index")
    public int index;
}
