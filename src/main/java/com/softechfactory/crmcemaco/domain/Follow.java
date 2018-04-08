package com.softechfactory.crmcemaco.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Follow.
 */
@Entity
@Table(name = "follow")
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origin_contact")
    private String originContact;

    @Column(name = "sub_origin_contact")
    private String subOriginContact;

    @Column(name = "result")
    private String result;

    @Column(name = "next_contact_date")
    private String nextContactDate;

    @Column(name = "registry_date")
    private String registryDate;

    @Column(name = "status")
    private String status;

    @Column(name = "next_contact_reason")
    private String nextContactReason;

    @Column(name = "favorite_card")
    private String favoriteCard;

    @Column(name = "buy_in_cemaco")
    private String buyInCemaco;

    @Column(name = "interested_buy")
    private String interestedBuy;

    @Column(name = "article")
    private String article;

    @ManyToOne
    private Contact contact;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginContact() {
        return originContact;
    }

    public Follow originContact(String originContact) {
        this.originContact = originContact;
        return this;
    }

    public void setOriginContact(String originContact) {
        this.originContact = originContact;
    }

    public String getSubOriginContact() {
        return subOriginContact;
    }

    public Follow subOriginContact(String subOriginContact) {
        this.subOriginContact = subOriginContact;
        return this;
    }

    public void setSubOriginContact(String subOriginContact) {
        this.subOriginContact = subOriginContact;
    }

    public String getResult() {
        return result;
    }

    public Follow result(String result) {
        this.result = result;
        return this;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNextContactDate() {
        return nextContactDate;
    }

    public Follow nextContactDate(String nextContactDate) {
        this.nextContactDate = nextContactDate;
        return this;
    }

    public void setNextContactDate(String nextContactDate) {
        this.nextContactDate = nextContactDate;
    }

    public String getRegistryDate() {
        return registryDate;
    }

    public Follow registryDate(String registryDate) {
        this.registryDate = registryDate;
        return this;
    }

    public void setRegistryDate(String registryDate) {
        this.registryDate = registryDate;
    }

    public String getStatus() {
        return status;
    }

    public Follow status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNextContactReason() {
        return nextContactReason;
    }

    public Follow nextContactReason(String nextContactReason) {
        this.nextContactReason = nextContactReason;
        return this;
    }

    public void setNextContactReason(String nextContactReason) {
        this.nextContactReason = nextContactReason;
    }

    public String getFavoriteCard() {
        return favoriteCard;
    }

    public Follow favoriteCard(String favoriteCard) {
        this.favoriteCard = favoriteCard;
        return this;
    }

    public void setFavoriteCard(String favoriteCard) {
        this.favoriteCard = favoriteCard;
    }

    public String getBuyInCemaco() {
        return buyInCemaco;
    }

    public Follow buyInCemaco(String buyInCemaco) {
        this.buyInCemaco = buyInCemaco;
        return this;
    }

    public void setBuyInCemaco(String buyInCemaco) {
        this.buyInCemaco = buyInCemaco;
    }

    public String getInterestedBuy() {
        return interestedBuy;
    }

    public Follow interestedBuy(String interestedBuy) {
        this.interestedBuy = interestedBuy;
        return this;
    }

    public void setInterestedBuy(String interestedBuy) {
        this.interestedBuy = interestedBuy;
    }

    public String getArticle() {
        return article;
    }

    public Follow article(String article) {
        this.article = article;
        return this;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Contact getContact() {
        return contact;
    }

    public Follow contact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
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
        Follow follow = (Follow) o;
        if (follow.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), follow.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Follow{" +
            "id=" + getId() +
            ", originContact='" + getOriginContact() + "'" +
            ", subOriginContact='" + getSubOriginContact() + "'" +
            ", result='" + getResult() + "'" +
            ", nextContactDate='" + getNextContactDate() + "'" +
            ", registryDate='" + getRegistryDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", nextContactReason='" + getNextContactReason() + "'" +
            ", favoriteCard='" + getFavoriteCard() + "'" +
            ", buyInCemaco='" + getBuyInCemaco() + "'" +
            ", interestedBuy='" + getInterestedBuy() + "'" +
            ", article='" + getArticle() + "'" +
            "}";
    }
}
