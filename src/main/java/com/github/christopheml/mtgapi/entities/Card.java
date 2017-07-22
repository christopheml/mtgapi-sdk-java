package com.github.christopheml.mtgapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents a Magic Card.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {

    private String name;

    private String manaCost;

    private int cmc;

    private String type;

    private String text;

    private String artist;

    private String number;

    private long multiverseid;

    private String imageUrl;

    private String watermark;

    private String originalText;

    private String originalType;

    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public int getCmc() {
        return cmc;
    }

    public void setCmc(int cmc) {
        this.cmc = cmc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getMultiverseid() {
        return multiverseid;
    }

    public void setMultiverseid(long multiverseid) {
        this.multiverseid = multiverseid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWatermark() {
        return watermark;
    }

    public void setWatermark(String watermark) {
        this.watermark = watermark;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getOriginalType() {
        return originalType;
    }

    public void setOriginalType(String originalType) {
        this.originalType = originalType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}