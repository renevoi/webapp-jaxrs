package com.rene.messenger.resources;



import com.rene.messenger.model.Profile;
import com.rene.messenger.service.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/profiles")
public class ProfileResource {

    private ProfileService profileService = new ProfileService();


    @GET
    public List<Profile> getAllProfile(){
        return profileService.getAllProfile();
    }

    @GET
    @Path("/{profile}")
    public Profile getMessage(@PathParam("profile") String profileName){
        return profileService.getProfile(profileName);
    }

    @POST
    @Path("/addProfile")
    public Profile addProfile(Profile profile){
        return profileService.addProfile(profile);
    }

    @PUT
    @Path("/{profile}")
    public Profile updateMessage(@PathParam("profile") String profileName, Profile profile){
        profile.setProfileName(profileName);
        return profileService.updateProfile(profile);
    }

    @DELETE
    @Path("/{profile}")
    public Profile deleteProfile(@PathParam("profile") String profileName){
        return profileService.removeProfile(profileName);
    }
}
