package com.samplemgt.downjacket.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "downjacket")
public class Jacket {

    @Id
    public String id;

    @JsonProperty("jacketId")
    @Field("jacketId")
    public String jacketId;

    @JsonProperty("type")
    @Field("type")
    public String type;

    @JsonProperty("editor")
    @TextIndexed(weight=5)
    @Field("editor")
    public String editor;

    @JsonProperty("designer")
    @TextIndexed(weight=5)
    @Field("designer")
    public String designer;

    @JsonProperty("tailor")
    @TextIndexed(weight=5)
    @Field("tailor")
    public String tailor;

    @JsonProperty("status")
    @Field("status")
    public String status;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @JsonProperty("createDate")
    @Field("createDate")
    public Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @JsonProperty("saleDate")
    @Field("saleDate")
    public Date saleDate;


    @JsonProperty("comment")
    @Field("comment")
    public String comment;

    @JsonProperty("customer")
    @Field("customer")
    public String customer;

    @JsonProperty("attribute")
    @Field("attribute")
    public String attribute;


}
