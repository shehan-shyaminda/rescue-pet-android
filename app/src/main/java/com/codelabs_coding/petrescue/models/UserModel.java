package com.codelabs_coding.petrescue.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserModel implements Serializable {
    private User user;

    @SerializedName("access_token")
    private String accessToken;

    public User getUser() {
        return user;
    }

    public void setUser(User value) {
        this.user = value;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String value) {
        this.accessToken = value;
    }

    public static class User implements Serializable {

        @SerializedName("_id")
        private String userId;

        private String username;

        @SerializedName("userPassword")
        private String password;

        @SerializedName("userPets")
        private List<Pet> pets;
        private double userLatitude;
        private double userLongitude;

        public String getId() {
            return userId;
        }

        public void setId(String userId) {
            this.userId = userId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String value) {
            this.userId = value;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String value) {
            this.username = value;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String value) {
            this.password = value;
        }

        public List<Pet> getPets() {
            return pets;
        }

        public void setPets(List<Pet> value) {
            this.pets = value;
        }

        public double getUserLatitude() {
            return userLatitude;
        }

        public void setUserLatitude(double value) {
            this.userLatitude = value;
        }

        public double getUserLongitude() {
            return userLongitude;
        }

        public void setUserLongitude(double value) {
            this.userLongitude = value;
        }

        public static class Pet implements Serializable {

            private String petsId;
            private String petsNickname;
            private String petsType;

            public String getPetsId() {
                return petsId;
            }

            public void setPetsId(String petsId) {
                this.petsId = petsId;
            }

            public String getPetsNickname() {
                return petsNickname;
            }

            public void setPetsNickname(String petsNickname) {
                this.petsNickname = petsNickname;
            }

            public String getPetsType() {
                return petsType;
            }

            public void setPetsType(String petsType) {
                this.petsType = petsType;
            }
        }
    }
}



