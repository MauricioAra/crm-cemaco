package com.softechfactory.crmcemaco.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Follow entity.
 */
public class FollowDTO implements Serializable {

    private Long id;

    private String originContact;

    private String subOriginContact;

    private String result;

    private String nextContactDate;

    private String registryDate;

    private String status;

    private String nextContactReason;

    private String favoriteCard;

    private String buyInCemaco;

    private String interestedBuy;

    private String article;

    private Long contactId;

    private String contactName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginContact() {
        return originContact;
    }

    public void setOriginContact(String originContact) {
        this.originContact = originContact;
    }

    public String getSubOriginContact() {
        return subOriginContact;
    }

    public void setSubOriginContact(String subOriginContact) {
        this.subOriginContact = subOriginContact;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNextContactDate() {
        return nextContactDate;
    }

    public void setNextContactDate(String nextContactDate) {
        this.nextContactDate = nextContactDate;
    }

    public String getRegistryDate() {
        return registryDate;
    }

    public void setRegistryDate(String registryDate) {
        this.registryDate = registryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNextContactReason() {
        return nextContactReason;
    }

    public void setNextContactReason(String nextContactReason) {
        this.nextContactReason = nextContactReason;
    }

    public String getFavoriteCard() {
        return favoriteCard;
    }

    public void setFavoriteCard(String favoriteCard) {
        this.favoriteCard = favoriteCard;
    }

    public String getBuyInCemaco() {
        return buyInCemaco;
    }

    public void setBuyInCemaco(String buyInCemaco) {
        this.buyInCemaco = buyInCemaco;
    }

    public String getInterestedBuy() {
        return interestedBuy;
    }

    public void setInterestedBuy(String interestedBuy) {
        this.interestedBuy = interestedBuy;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FollowDTO followDTO = (FollowDTO) o;
        if(followDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), followDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FollowDTO{" +
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
