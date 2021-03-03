package com.cardemo.backend.generics.datatypes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CarType {

    @JsonProperty("D")
    D("D", "SEDAN"),
    @JsonProperty("C")
    C("C", "COUPE"),
    @JsonProperty("S")
    S("S", "SPORT");

    @Getter
    private String id;
    @Getter
    private String name;

    CarType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CarType getById(String id) {
        return Stream.of(CarType.values()).filter(type -> type.id.equals(id)).findFirst().orElse(null);
    }
}
