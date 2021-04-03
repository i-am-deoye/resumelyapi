package com.resumly.resumeapi.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Flag {
    NONE(Flag.NONE_ID), NO(Flag.NO_ID), YES(Flag.YES_ID), DISABLED(Flag.DISABLED_ID), ENABLED(Flag.ENABLED_ID), INACTIVE(Flag.INACTIVE_ID), ACTIVE(
            Flag.ACTIVE_ID), LOCKED(Flag.LOCKED_ID), EXPIRED(Flag.EXPIRED_ID), DELETED(Flag.DELETED_ID), OPEN(Flag.OPEN_ID), CLOSED(
            Flag.CLOSED_ID), PROCESSING(Flag.PROCESSING_ID, "P"), APPROVED(Flag.APPROVED_ID, "A"), REJECTED(Flag.REJECTED_ID, "R");

    public static final int NONE_ID = 0;

    public static final int NO_ID = 1;

    public static final int YES_ID = 2;

    public static final int DISABLED_ID = 3;

    public static final int ENABLED_ID = 4;

    public static final int INACTIVE_ID = 5;

    public static final int ACTIVE_ID = 6;

    public static final int LOCKED_ID = 7;

    public static final int EXPIRED_ID = 8;

    public static final int DELETED_ID = 9;

    public static final int OPEN_ID = 10;

    public static final int CLOSED_ID = 11;

    public static final int PROCESSING_ID = 12;

    public static final int APPROVED_ID = 13;

    public static final int REJECTED_ID = 14;

    private int id;

    private String name;


    Flag(int id) {
        this.id = id;
    }


    Flag(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public static Flag getFlag(int id) {
        return Arrays.stream(Flag.values()).filter(flag -> flag.getId() == id).findFirst().orElse(Flag.NONE);
    }


    public int getId() {
        return id;
    }


    public String getName() {
        return this.name != null ? this.name : name();
    }


    public int getOrdinal() {
        return this.ordinal();
    }
}
