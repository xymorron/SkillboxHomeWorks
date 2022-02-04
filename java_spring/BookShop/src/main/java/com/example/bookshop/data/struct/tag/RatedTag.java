package com.example.bookshop.data.struct.tag;

import lombok.Getter;

@Getter
public class RatedTag {

    private final int id;
    private final String name;
    private final String sizeIdentifier;

    public RatedTag(TagEntity tag, String sizeIdentifier) {
        this.id = tag.getId();
        this.name = tag.getName();
        this.sizeIdentifier = sizeIdentifier;
    }

}
