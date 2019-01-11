package com.rene.messenger.resources;

import com.rene.messenger.model.Message;
import com.rene.messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/messages")
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getJsonMessages(/*@BeanParam MessageFilterBean filterBean
    									@QueryParam("year") int year,
                                        @QueryParam("start") int start,
                                        @QueryParam("size") int size*/){
    	System.out.println("Json Method called");
    	
    	/*if (filterBean.getYear() > 0){
            return messageService.getAllMessageForYear(filterBean.getYear());
        }

        if (filterBean.getStart() >= 0 && filterBean.getSize() >= 0){
            return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
        }*/
    	
    	return messageService.getAllMessage();
    }
    
    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Message> getXmlMessages(/*@BeanParam MessageFilterBean filterBean
    									@QueryParam("year") int year,
                                        @QueryParam("start") int start,
                                        @QueryParam("size") int size*/){
        /*if (filterBean.getYear() > 0){
            return messageService.getAllMessageForYear(filterBean.getYear());
        }

        if (filterBean.getStart() >= 0 && filterBean.getSize() >= 0){
            return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
        }*/
    	System.out.println("XML method called");
        return messageService.getAllMessage();
    }

    @GET
    @Path("/{messageid}")
    //@Produces(MediaType.APPLICATION_JSON)
    public Message getMessage(@PathParam("messageid") int id, @Context UriInfo uriInfo){
        Message message = messageService.getMessage(id);
        /*String uri = uriInfo.getBaseUriBuilder() //basepath: http://localhost:8080/messenger/webapi
        		.path(MessageResource.class)							//					/messages
        		.path(Integer.toString(message.getId()))				//					/{messageId}
        		.build()
        		.toString();
        message.addLink(uri, "self");*/
        			
        message.addLink(getUriForSelf(uriInfo, message), "self");
        message.addLink(getUriForProfile(uriInfo, message), "profile");
        message.addLink(getUriForComments(uriInfo, message), "comments");
        return message;
    }

	private String getUriForComments(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder()//basepath: http://localhost:8080/messenger/webapi
				.path(MessageResource.class)						//					/messages
				.path(MessageResource.class, "getCommentResource")	// 				/{messageId}
				.path(CommentResource.class)							// 				/comments		
				.resolveTemplate("messageId", message.getId()) // this will switch the message.getId() to messageId
				.build();
		return uri.toString();
		
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder()//basepath: http://localhost:8080/messenger/webapi
				.path(ProfileResource.class)						//					/profiles
				.path(message.getAuthor())						//					/{authorName}
				.build();
		return uri.toString();
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder() //basepath: http://localhost:8080/messenger/webapi
        		.path(MessageResource.class)							//					/messages
        		.path(Integer.toString(message.getId()))				//					/{messageId}
        		.build()
        		.toString();
		return uri;
	}

	@POST
	@Path("/addMessage")
	@Consumes(MediaType.TEXT_XML) // it accepts xml
	//@Produces(MediaType.APPLICATION_JSON) // it returns/produce json
	public Response addXmlMessage(Message message, @Context UriInfo uriInfo){
		
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri =  uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				.entity(newMessage)
				.build();
		
		//return messageService.addMessage(message);
	}
    
	@POST
    @Path("/addMessage")
    @Consumes(MediaType.APPLICATION_JSON) // it accepts json
    //@Produces(MediaType.APPLICATION_JSON) // it returns/produce json
    public Response addJsonMessage(Message message, @Context UriInfo uriInfo){
    	
    	Message newMessage = messageService.addMessage(message);
    	String newId = String.valueOf(newMessage.getId());
    	URI uri =  uriInfo.getAbsolutePathBuilder().path(newId).build();
    	return Response.created(uri)
    			.entity(newMessage)
    			.build();
        
    	//return messageService.addMessage(message);
    }

    @PUT
    @Path("/{messageid}")
    //@Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    public Message updateMessage(@PathParam("messageid") int id, Message message){
        message.setId(id);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageid}")
    //@Produces(MediaType.APPLICATION_JSON)
    public Message deleteMessage(@PathParam("messageid") int id){
        return messageService.removeMessage(id);
    }
    
    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
    	return new CommentResource();
    }

}
