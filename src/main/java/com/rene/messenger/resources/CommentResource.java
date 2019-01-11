package com.rene.messenger.resources;

import com.rene.messenger.model.Comment;
import com.rene.messenger.service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	private CommentService commentService = new CommentService();
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") int id){
		return commentService.getAllComments(id);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") int id, Comment comment) {
		return commentService.addComment(id, comment);
	}
	
	@PUT
	@Path("{commentId}")
	public Comment updateComment(@PathParam("messageId") int id, @PathParam("commentId") int commentId, Comment comment) {
		comment.setId(commentId);
		return commentService.updateComment(id, comment);
	}
	
	@DELETE
	@Path("{commentId}")
	public void deleteComment(@PathParam("messageId") int id, @PathParam("commentId") int commentId) {
		commentService.removeComment(id, commentId);
	}
	
	@GET
	@Path("{commentId}")
	public Comment getComment(@PathParam("messageId") int id, @PathParam("commentId") int commentId) {
		return commentService.getComment(id, commentId);
	}
	
	
	
	
	
	
	/*@GET
	public String getComments() {
		return "comments";
	}
	
	@GET
	@Path("{commentId}")
	public String getMessageWithComments(@PathParam("messageId") int msgId, @PathParam("commentId") int commentId) {
		return "from messageId: " + msgId + " from commentId : " + commentId;
	}*/
	
	
}
