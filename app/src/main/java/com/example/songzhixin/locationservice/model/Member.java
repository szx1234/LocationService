package com.example.songzhixin.locationservice.model;

/**
 * Created by songzhixin on 2017/7/20.
 */

public class Member {
    public String user_name;
    public String avatar_url;
    public String nickname;
    public int memeber_role;
    public Position position;

    public String toString() {
        return user_name + " " + nickname + " " + avatar_url + " " + memeber_role + position;
    }
}
