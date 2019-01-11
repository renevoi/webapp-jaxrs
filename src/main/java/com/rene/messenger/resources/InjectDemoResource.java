package com.rene.messenger.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
@Path("/injectdemo")
public class InjectDemoResource {
	
	@GET
	@Path("/annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
											@HeaderParam("header") String header,
											@CookieParam("cookieName") String cookie) {
		return "Matrix param: " + matrixParam + " Header param " + header + " Cookie param " + cookie;
	}


	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo info, @Context HttpHeaders headers) {
		
		String path = info.getAbsolutePath().toString();
		String cookies = headers.getCookies().toString();
		
		return "Path : " + path + " Cookies : " + cookies;
	}
}










