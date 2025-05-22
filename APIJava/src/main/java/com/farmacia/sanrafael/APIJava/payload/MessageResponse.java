package com.farmacia.sanrafael.APIJava.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@ToString
@Data
public class MessageResponse implements Serializable {
    private String message;
    private Object data;
    private LocalDateTime timestamp;
}
