package com.trinet.connecto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusCounts {
    Integer status;
    Integer count;
    String statusText;
}
