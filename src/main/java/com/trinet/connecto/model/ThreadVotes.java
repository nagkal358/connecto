package com.trinet.connecto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThreadVotes {
    Integer threadId;
    String title;
    Integer noOfVotes;
    VoteCounts votes;
}
