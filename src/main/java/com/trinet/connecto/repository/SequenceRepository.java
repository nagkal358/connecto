package com.trinet.connecto.repository;

public interface SequenceRepository {
    long getNextSequenceId(String collectionName) throws Exception;
}
