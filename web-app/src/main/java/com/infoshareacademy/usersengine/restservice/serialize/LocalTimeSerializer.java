package com.infoshareacademy.usersengine.restservice.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeSerializer extends StdSerializer<LocalTime> {

    private static final long serialVersionUID = 1L;

    public LocalTimeSerializer(){
        super(LocalTime.class);
    }

    @Override
    public void serialize(LocalTime value, JsonGenerator generator, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        generator.writeString(value.format(DateTimeFormatter.ISO_LOCAL_TIME));
    }
}
