package com.rene.messenger.service;

import com.rene.messenger.database.DatabaseClass;
import com.rene.messenger.exception.DataNotFoundException;
import com.rene.messenger.model.Message;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


public class MessageService {

    private Map<Integer, Message> messages = DatabaseClass.getMessages();

    public MessageService(){
        messages.put(1, new Message(1, "Hello Ailia", "Renevoi"));
        messages.put(2, new Message(2, "Hello Grace", "Renevoi"));
    }


    public List<Message> getAllMessageForYear(int year){
        List<Message> messagesForYear = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (Message message:
             messages.values()) {
            cal.setTime(message.getCreated());
            if (cal.get(Calendar.YEAR) == year){
                messagesForYear.add(message);
            }
        }
        return messagesForYear;
    }

    public List<Message> getAllMessagesPaginated(int start, int size){
        ArrayList<Message> list = new ArrayList<>(messages.values());
        if (start+size > list.size()) return new ArrayList<Message>();
        return list.subList(start, start+size);
    }




    public List<Message> getAllMessage(){

        return new ArrayList<>(messages.values());

        /*GenericEntity<List<Message>> entity = new GenericEntity<List<Message>>(list) {};
        Response response = Response.ok(entity).build();*//*
        
        Message m1 = new Message(1, "Hello Ailia", "Renevoi");
        Message m2 = new Message(2, "Hello Grace", "Renevoi");
        		
        list.add(m1);
        list.add(m2);

        List<Message> list = new ArrayList<Message>(){
            {
               add(new Message(1, "Hello Ailia", "Rene"));
               add(new Message(2, "Hello Grace", "Rene"));
            }

        };

        return list;*/
    }

    public Message getMessage(Integer id){
        Message message = messages.get(id);
        if (message == null) {
			throw new DataNotFoundException("Message with id " + id + " not found");
		}
        return message;
    }

    public Message addMessage(Message message){
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message){
        if (message.getId() <= 0) {
            return null;
        }
        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(Integer id){
        return messages.remove(id);
    }

}
