package org.maxur.commons.domain;

/**
 * The AbstractEntity is base class of domain layer.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>06.07.11</pre>
 */
public abstract class AbstractEntity implements Entity {

    private static final long serialVersionUID = 2853087286450189484L;
    /**
     * The Employee's Id.
     */
    private String identifier;

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
    protected void setIdentifier(final String value) {
        this.identifier = value;
    }


    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractEntity)) {
            return false;
        }
        final AbstractEntity entity = (AbstractEntity) obj;
        return (identifier != null
                ? identifier.equals(entity.identifier)
                : false);
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
