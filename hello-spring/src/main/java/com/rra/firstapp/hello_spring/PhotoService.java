package com.rra.firstapp.hello_spring;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PhotoService {

    private final Map<String, Hello> db = new HashMap<>(){{
        put("1", new Hello("1", "hello.png"));
    }};

    public Collection<Hello> get() {
        return db.values();
    }

    public Hello get(String id) {
        return db.get(id);
    }

    public Hello remove(String id) {
        return db.remove(id);
    }

    public Hello save(String fileName, byte[] data) {
        Hello hello = new Hello();
        hello.setId(UUID.randomUUID().toString());
        hello.setFileName(fileName);
        hello.setData(data);
        db.put(hello.getId(), hello);
        return  hello;
    }
}
