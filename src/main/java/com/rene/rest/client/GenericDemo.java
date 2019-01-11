package com.rene.rest.client;

import com.rene.messenger.model.Message;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class GenericDemo {

    public static void main(String[] args){


        Client client = ClientBuilder.newClient();

        /* ============  Best Practices for wrinting client code ======================= */

        List<Message> messages = client.target("http://localhost:8085/webapp-jaxrs/webapi/")
                .path("messages")
                .queryParam("year", 2018)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Message>>(){});

        System.out.println(messages);
    }

}
