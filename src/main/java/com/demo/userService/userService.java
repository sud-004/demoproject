package com.demo.userService;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.demo.domain.User;

public interface userService {

	User createUser(User user);

	Boolean authenticateLogin(String email, String password);

	List<User> getusersByName(String name);

	Response uploadUserPic(InputStream uploadedInputStream, FormDataContentDisposition fileDetail);

	Response uploadWithPic(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, User userData);

}
