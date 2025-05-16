package com.farmacia.sanrafael.APIJava.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

public class MessageResponse implements Serializable {
    @Builder
    @ToString
    @Data

    private string message;
    private object data;



}
