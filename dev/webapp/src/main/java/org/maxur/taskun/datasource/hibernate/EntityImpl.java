package org.maxur.taskun.datasource.hibernate;


import org.hibernate.annotations.GenericGenerator;
import org.maxur.commons.domain.BaseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/26/11
 */
@Component
@Entity
@Table(name = "ENTITY")
@Inheritance(strategy = InheritanceType.JOINED)
//@MappedSuperclass
public abstract class EntityImpl<T extends BaseEntity> implements org.maxur.commons.domain.Entity {

    private static final long serialVersionUID = 6256559380130002522L;

    private T entity;

    private Integer version;

    public EntityImpl() {
    }

    public EntityImpl(final T entity) {
        this.entity = entity;
    }

    @Transient
    public T getEntity() {
        return entity;
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ENTITY_ID")
    public String getIdentifier() {
        return this.entity.getIdentifier();
    }

    public void setIdentifier(final String id) {
        this.entity.setIdentifier(id);
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
