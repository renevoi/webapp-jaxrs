package com.rene.rest.client;

import com.rene.messenger.model.Message;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestApiClient {


    public static void main(String[] args){

        Client client = ClientBuilder.newClient();

        /* ============  Best Practices for wrinting client code ======================= */

        WebTarget baseTarget = client.target("http://localhost:8085/webapp-jaxrs/webapi/");
        WebTarget messagesTarget = baseTarget.path("messages");
        WebTarget singleMessage = messagesTarget.path("{messageId}");

        Message message1 = singleMessage
                            .resolveTemplate("messageId", "1")
                            .request(MediaType.APPLICATION_JSON)
                            .get(Message.class);

        Message message2 = singleMessage
                            .resolveTemplate("messageId", "2")
                            .request(MediaType.APPLICATION_JSON)
                            .get(Message.class);

        Message newMessage = new Message(3, "My new message from jax-rs client", "Renevoi");
        Response postResponse = messagesTarget
                                .request()
                                .post(Entity.json(newMessage));

        Message createdMessage = postResponse.readEntity(Message.class);
        System.out.println(createdMessage.getMessage());
        /*========================== GET ==============================*/

       /* Response response = client
                            .target("http://localhost:8085/webapp-jaxrs/webapi/messages/1")
                            .request()
                            .get();

        Message message = client
                            .target("http://localhost:8085/webapp-jaxrs/webapi/messages/2")
                            .request(MediaType.APPLICATION_JSON)
                            .get(Message.class);

        String message  =   client
                            .target("http://localhost:8085/webapp-jaxrs/webapi/messages/2")
                            .request(MediaType.APPLICATION_JSON)
                            .get(String.class);
        
        WebTarget target = client.target("http://localhost:8085/webapp-jaxrs/webapi/messages/1");
        Builder request = target.request();
        Response response = request.get();*/

        //Message message = response.readEntity(Message.class);
//      System.out.println(message1.getMessage());
//      System.out.println(message2.getMessage());
        //System.out.println(message);
    }


}
