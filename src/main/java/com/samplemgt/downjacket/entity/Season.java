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
@Document(collection = "season")
public class Season {

    @Id
    public String id;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @JsonProperty("startDate")
    @Field("startDate")
    public Date startDate;


}
