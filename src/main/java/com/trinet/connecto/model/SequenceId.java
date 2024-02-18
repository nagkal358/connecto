package com.trinet.connecto.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "sequence")
public class SequenceId {
    @Id
    private String id;
    private long seq;
}
