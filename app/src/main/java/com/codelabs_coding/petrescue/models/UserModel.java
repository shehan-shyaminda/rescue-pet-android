package com.codelabs_coding.petrescue.models;

import java.io.Serializable;
import java.util.List;

public class UserModel implements Serializable {
    private User user;
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

    public class User implements Serializable{
        private String id;
        private String userId;
        private String username;
        private String password;
        private List<Pet> pets;
        private long v;
        private double userLatitude;
        private double userLongitude;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public long getV() {
            return v;
        }

        public void setV(long value) {
            this.v = value;
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

        public class Pet implements Serializable {
            private String petId;
            private String userId;
            private String petDeviceId;
            private String petNickname;
            private String petType;
            private String petBread;
            private String petLatitude;
            private String petLongitude;
            private List<LocationHistory> locationHistory;

            public String getPetId() {
                return petId;
            }

            public void setPetId(String value) {
                this.petId = value;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String value) {
                this.userId = value;
            }

            public String getPetDeviceId() {
                return petDeviceId;
            }

            public void setPetDeviceId(String value) {
                this.petDeviceId = value;
            }

            public String getPetNickname() {
                return petNickname;
            }

            public void setPetNickname(String value) {
                this.petNickname = value;
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

            public String getPetLatitude() {
                return petLatitude;
            }

            public void setPetLatitude(String value) {
                this.petLatitude = value;
            }

            public String getPetLongitude() {
                return petLongitude;
            }

            public void setPetLongitude(String value) {
                this.petLongitude = value;
            }

            public List<LocationHistory> getLocationHistory() {
                return locationHistory;
            }

            public void setLocationHistory(List<LocationHistory> locationHistory) {
                this.locationHistory = locationHistory;
            }

            public class LocationHistory implements Serializable{
                private long dateTime;
                private String longitude;
                private String latitude;

                public long getDateTime() { return dateTime; }
                public void setDateTime(long value) { this.dateTime = value; }

                public String getLongitude() { return longitude; }
                public void setLongitude(String value) { this.longitude = value; }

                public String getLatitude() { return latitude; }
                public void setLatitude(String value) { this.latitude = value; }
            }

        }
    }
}



