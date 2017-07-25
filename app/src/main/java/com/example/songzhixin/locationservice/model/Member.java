package com.example.songzhixin.locationservice.model;

import java.util.HashMap;

/**
 * Created by songzhixin on 2017/7/20.
 */

public class Member {
    public String user_name;
    public String avatar_url;
    public String nickname;
    public int memeber_role;
    public Position position;

    HashMap<String, Member> map = new HashMap<>();
    public static void main(String a[]){
        Member member = new Member();
        member.map.put("123", new Member());
        member.map.put("13", new Member());
        System.out.println(member.map.get("123") + "      " +member.map.size());
    }
}
