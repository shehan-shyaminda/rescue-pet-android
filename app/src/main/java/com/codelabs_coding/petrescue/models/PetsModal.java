package com.codelabs_coding.petrescue.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class PetsModal implements Serializable {

    @SerializedName("_id")
    private String petId;

    private String userId;
    private String petNickname;
    private String petType;
    private String petBread;
    private ArrayList<PetLocation> petLocationHistory;

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getPetNickname() {
        return petNickname;
    }

    public void setPetNickname(String petNickname) {
        this.petNickname = petNickname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetBread() {
        return petBread;
    }

    public void setPetBread(String petBread) {
        this.petBread = petBread;
    }

    public ArrayList<PetLocation> getPetLocationHistory() {
        return petLocationHistory;
    }

    public void setPetLocationHistory(ArrayList<PetLocation> petLocationHistory) {
        this.petLocationHistory = petLocationHistory;
    }

    public static class PetLocation implements Serializable {
        private Double petLongitude;
        private Double petLatitude;
        private long timeStamp;

        public Double getPetLongitude() {
            return petLongitude;
        }

        public void setPetLongitude(Double petLongitude) {
            this.petLongitude = petLongitude;
        }

        public Double getPetLatitude() {
            return petLatitude;
        }

        public void setPetLatitude(Double petLatitude) {
            this.petLatitude = petLatitude;
        }

        public long getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
        }
    }
}