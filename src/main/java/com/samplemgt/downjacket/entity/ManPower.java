package com.samplemgt.downjacket.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "manpower")
public class ManPower {

    @Id
    public String id;

    @JsonProperty("name")
    @Field("name")
    public String name;

    @JsonProperty("type")
    @Field("type")
    public String type;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @JsonProperty("createDate")
    @Field("createDate")
    public Date createDate;
}
