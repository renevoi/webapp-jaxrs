package com.rene.messenger.service;

import com.rene.messenger.database.DatabaseClass;
import com.rene.messenger.model.Comment;
import com.rene.messenger.model.ErrorMessage;
import com.rene.messenger.model.Message;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CommentService {
	
	private Map<Integer, Message> messages = DatabaseClass.getMessages();
	
	
	public List<Comment> getAllComments(int messageId){
		Map<Integer, Comment> commets = messages.get(messageId).getComments();
		return new ArrayList<Comment>(commets.values());
	}
	
	public Comment getComment(int messageId, int commetnId) {
		ErrorMessage errorMessage = new ErrorMessage("not found", 404, "http://google.com");
		Response response = Response.status(Status.NOT_FOUND) // you don't have to do this
				.entity(errorMessage)						// if you implemented the NotFoundException
				.build();							// from the subclass/impl of WebApplicationException
		Message message = messages.get(messageId);
		if (message == null) {
			throw new WebApplicationException(response);
		}
		Map<Integer, Comment> comments = messages.get(messageId).getComments();
		Comment comment = comments.get(commetnId);
		if (comment == null) {
			throw new NotFoundException(response);
		}
		
		return comment;
	}
	
	public Comment addComment(int messageId, Comment comment) {
		Map<Integer, Comment> comments =  messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(int messageId, Comment comment) {
		Map<Integer, Comment> comments = messages.get(messageId).getComments();
		if(comment.getId() <= 0) {
			return null;
		}
		
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(int messageId, int commentId) {
		Map<Integer, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
}











