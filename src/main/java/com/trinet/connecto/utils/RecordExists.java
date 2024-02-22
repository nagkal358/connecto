package com.trinet.connecto.utils;

import org.bson.json.JsonObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RecordExists {
    public Map<String, String> recordExists(String recordName) {
        Map<String, String> m = new HashMap<>();
        m.put("message", recordName + " Already Exists");
        return m;
    }
}
