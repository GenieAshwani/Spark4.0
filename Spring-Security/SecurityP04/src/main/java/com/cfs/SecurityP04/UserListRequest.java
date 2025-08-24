package com.cfs.SecurityP04;

import java.util.List;

public class UserListRequest {

    private List<UserRequest> users;

    public List<UserRequest> getUsers() {
        return users;
    }

    public void setUsers(List<UserRequest> users) {
        this.users = users;
    }
}
