package com.infy.tele.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Train.
 */
@Entity
@Table(name = "train")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Train implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "addr")
    private String addr;

    @Column(name = "contact_number")
    private Integer contactNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Train name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public Train addr(String addr) {
        this.addr = addr;
        return this;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getContactNumber() {
        return contactNumber;
    }

    public Train contactNumber(Integer contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public void setContactNumber(Integer contactNumber) {
        this.contactNumber = contactNumber;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Train train = (Train) o;
        if (train.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), train.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Train{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", addr='" + getAddr() + "'" +
            ", contactNumber=" + getContactNumber() +
            "}";
    }
}
