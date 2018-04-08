package com.softechfactory.crmcemaco.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Contact.
 */
@Entity
@Table(name = "contact")
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "social_id")
    private String socialId;

    @Column(name = "sex")
    private String sex;

    @Column(name = "registry_date")
    private String registryDate;

    @Column(name = "update_date")
    private String updateDate;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "contact")
    @JsonIgnore
    private Set<Follow> follows = new HashSet<>();

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

    public Contact name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public Contact firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Contact lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public Contact email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSocialId() {
        return socialId;
    }

    public Contact socialId(String socialId) {
        this.socialId = socialId;
        return this;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getSex() {
        return sex;
    }

    public Contact sex(String sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRegistryDate() {
        return registryDate;
    }

    public Contact registryDate(String registryDate) {
        this.registryDate = registryDate;
        return this;
    }

    public void setRegistryDate(String registryDate) {
        this.registryDate = registryDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public Contact updateDate(String updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getStatus() {
        return status;
    }

    public Contact status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Follow> getFollows() {
        return follows;
    }

    public Contact follows(Set<Follow> follows) {
        this.follows = follows;
        return this;
    }

    public Contact addFollow(Follow follow) {
        this.follows.add(follow);
        follow.setContact(this);
        return this;
    }

    public Contact removeFollow(Follow follow) {
        this.follows.remove(follow);
        follow.setContact(null);
        return this;
    }

    public void setFollows(Set<Follow> follows) {
        this.follows = follows;
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
        Contact contact = (Contact) o;
        if (contact.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contact.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Contact{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", socialId='" + getSocialId() + "'" +
            ", sex='" + getSex() + "'" +
            ", registryDate='" + getRegistryDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
