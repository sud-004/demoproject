package com.demo.controller;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.domain.User;
import com.demo.userService.userService;

@Component
@Path("/demo")
@Produces(value = { MediaType.APPLICATION_JSON })
@Consumes(value = { MediaType.APPLICATION_JSON })
public class MainController {
	@Autowired
	private userService userService;

	// @GET
	// public User getUser(@QueryParam("userid") String userId) {
	// System.err.println("hi");
	// User user = new User();
	// user.setEmail("sud2@abc");
	// user.setName("sudhanva2");
	// user.setPassword("sud2");
	// return userService.createUser(user);
	// }

	@GET
	@Path("getusers")
	public List<User> getusersByName(@QueryParam("name") String name) {
		return userService.getusersByName(name);
	}

	@POST
	@Path("create")
	public User createUser(User user) {
		return userService.createUser(user);
	}

	@GET
	@Path("login")
	public Boolean authenticateLogin(@QueryParam("email") String email, @QueryParam("password") String password) {
		System.err.println("inside login service");
		return userService.authenticateLogin(email, password);
	}

	@POST
	@Path("uploadpic")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadUserPic(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("userdata") User userData) {

		System.err.println("userData..." + userData);

		return userService.uploadUserPic(uploadedInputStream, fileDetail);

		// return null;
	}

	@POST
	@Path("createUserwithpic")

	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response createUserWithPic(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("userdata") User userData) {

		return userService.uploadWithPic(uploadedInputStream, fileDetail, userData);
	}

}
