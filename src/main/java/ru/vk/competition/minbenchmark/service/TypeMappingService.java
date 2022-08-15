package ru.vk.competition.minbenchmark.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TypeMappingService {

    final ConcurrentMap<String, Set<String>> mapping;

    public TypeMappingService() {
        mapping = new ConcurrentHashMap<>();
    }

    public void addMapping(String dbType, String type) {
        dbType = dbType.toUpperCase();
        type = type.toUpperCase();
        if (mapping.containsKey(dbType)) {
            mapping.get(dbType).add(type);
        }
        else {
            Set<String> value = new HashSet<>();
            value.add(type);
            mapping.put(dbType, value);
        }
    }

    public boolean hasMapping(String dbType, String type) {
        dbType = dbType.toUpperCase();
        type = type.toUpperCase();
        if (mapping.containsKey(dbType)) {
            return mapping.get(dbType).contains(type);
        }
        return false;
    }
}
