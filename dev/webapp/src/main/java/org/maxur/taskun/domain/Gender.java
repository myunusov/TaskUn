package org.maxur.taskun.domain;

/**
 */
public enum Gender {
    UNKNOWN('U'),
    MALE('M'),
    FEMALE('F');

    private Character id;

    private Gender(Character id) {
        this.id = id;
    }

    public Character getId() {
        return id;
    }

//TODO MY must be local
    @Override
    public String toString() {
        switch (this) {
            case UNKNOWN:
                return "unknown";
            case MALE:
                return "male";
            case FEMALE:
                return "female";
            default:
                return super.toString();
        }

    }
}