package com.rene.messenger.database;

import com.rene.messenger.model.Message;
import com.rene.messenger.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {

    private static Map<Integer, Message> messages = new HashMap<>();
    private static Map<String, Profile> profiles = new HashMap<>();


    public static Map<Integer, Message> getMessages(){
        return messages;
    }

    public static Map<String, Profile> getProfiles(){
        return profiles;
    }

}
