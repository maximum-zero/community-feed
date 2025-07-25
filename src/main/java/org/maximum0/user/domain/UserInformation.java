package org.maximum0.user.domain;

public class UserInformation {
    private final String userName;
    private final String profileImageUrl;

    public UserInformation(String userName, String profileImageUrl) {
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.userName = userName;
        this.profileImageUrl = profileImageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

}
