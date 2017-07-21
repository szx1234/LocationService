package com.example.songzhixin.locationservice.model;

import java.util.List;

/**
 * Created by songzhixin on 2017/7/20.
 */

public class MyActivity {
    public int status_code;
    public String desc;
    public Body body;
    public class Body {
        public int activity_id;
        public String activity_name;
        public String destination;
        public List<Member> members;
    }
}
