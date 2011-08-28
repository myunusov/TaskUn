package org.maxur.commons.domain;

/**
 * The BaseEntity is base class of domain layer.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>06.07.11</pre>
 */
public abstract class BaseEntity implements Entity {

    private static final long serialVersionUID = 2853087286450189484L;
    /**
     * The Employee's Id.
     */
    private String identifier;

    public BaseEntity(final String id) {
        setIdentifier(id);
    }

    /**
     * Getter for Entity's Identifier.
     * @return The Entity's Identifier.
     */
    @Override
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Setter for Identifier.
     * @param value The Identifier.
     */
    public void setIdentifier(final String value) {
        this.identifier = value;
    }


    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Entity)) {
            return false;
        }
        final Entity entity = (Entity) obj;
        return (identifier != null && identifier.equals(entity.getIdentifier()));
    }

    /**
     * @see Object#hashCode()
     * @return  a hash code value for this object.
     */
    @Override
    public final int hashCode() {
        return identifier != null ? identifier.hashCode() : 0;
    }

}
