package com.trinet.connecto.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ThreadStatusMapper {
    public Map<Integer, String> getMapping(){
        Map<Integer, String> statusMapper= new HashMap<>();
        statusMapper.put(0, "unknown");
        statusMapper.put(1, "submitted");
        statusMapper.put(2, "approved");
        statusMapper.put(3, "completed");
        return statusMapper;
    }
}
