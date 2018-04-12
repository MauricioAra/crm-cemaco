package com.softechfactory.crmcemaco.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Origin entity.
 */
public class OriginDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OriginDTO originDTO = (OriginDTO) o;
        if(originDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), originDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OriginDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
