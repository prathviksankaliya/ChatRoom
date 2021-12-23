package com.shadowtech.chatroom.Model;

public class UserProfile {
    String UserId,UserName,UserAbout,UserPhoneNumber,UserProfileImage;

    public UserProfile(String userId, String userName, String userAbout, String userPhoneNumber, String userProfileImage) {
        UserId = userId;
        UserName = userName;
        UserAbout = userAbout;
        UserPhoneNumber = userPhoneNumber;
        UserProfileImage = userProfileImage;
    }

    public UserProfile() {
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserAbout() {
        return UserAbout;
    }

    public void setUserAbout(String userAbout) {
        UserAbout = userAbout;
    }

    public String getUserPhoneNumber() {
        return UserPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        UserPhoneNumber = userPhoneNumber;
    }

    public String getUserProfileImage() {
        return UserProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        UserProfileImage = userProfileImage;
    }
}
