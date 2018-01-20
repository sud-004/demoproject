package com.demo.userService;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.demo.domain.User;
import com.demo.exception.UserException;
import com.demo.repository.userRepository;

@Service
public class userServiceImpl implements userService {

	@Autowired
	private userRepository userRepository;

	public static final Logger LOG = LoggerFactory.getLogger(userServiceImpl.class);;

	@Override
	public User createUser(User user) {
		if (!user.validate()) {
			throw new UserException("Please fill all details");

		}
		try {
			userRepository.save(user);
			return user;
		} catch (DataIntegrityViolationException e) {
			LOG.info(e.getMessage());
			throw new UserException("unable to save User as data entered is not valid");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info(e.getMessage());
			throw new UserException("unable to save User");
		}

	}

	@Override
	public Boolean authenticateLogin(String email, String password) {
		Objects.requireNonNull(email);
		Objects.requireNonNull(password);
		String user = userRepository.findByEmailAndPassword(email, password);
		if (user != null) {
			return true;
		} else
			throw new UserException("Unable to find user name or password");

	}

	@Override
	public List<User> getusersByName(String name) {
		if (name == null)
			throw new UserException("name cannot be Empty");
		List<User> users = userRepository.findByName(name);
		if (users != null && !users.isEmpty()) {
			return users;
		} else
			throw new UserException("Users with the name " + name + " not found");
	}

	@Override
	public Response uploadUserPic(InputStream uploadedInputStream, FormDataContentDisposition fileDetail) {
		if (uploadedInputStream != null && fileDetail != null) {
			byte[] buffer = new byte[10000];
			int bytesRead;
			try {
				OutputStream output = new FileOutputStream("/home/sudhanva/Desktop/UserPic/userPic.jpeg");

				while ((bytesRead = uploadedInputStream.read(buffer)) != -1) {
					output.write(buffer, 0, bytesRead);
				}
				return Response.status(Status.ACCEPTED).build();
			} catch (IOException e) {
				e.printStackTrace();
				LOG.info(e.getMessage());
				throw new UserException("File not uploaded");
			}
		} else {
			throw new UserException("File not found");
		}

	}

	@Override
	public Response uploadWithPic(InputStream uploadedInputStream, FormDataContentDisposition fileDetail,
			User userData) {
		if (uploadedInputStream != null && fileDetail != null) {
			if (!userData.validate()) {
				throw new UserException("Please fill all details");
			}

			byte[] buffer = new byte[10000];
			int bytesRead;
			try {
				ByteArrayOutputStream output = new ByteArrayOutputStream();

				while ((bytesRead = uploadedInputStream.read(buffer)) != -1) {
					output.write(buffer, 0, bytesRead);

				}
				userData.setUserIamge(output.toByteArray());
				try {
					userRepository.save(userData);
				} catch (DataIntegrityViolationException e) {
					LOG.info(e.getMessage());
					throw new UserException("unable to save User as data entered is not valid");
				} catch (Exception e) {
					LOG.info(e.getMessage());
					e.printStackTrace();
					throw new UserException("unable to save User");
				}
				return Response.status(Status.ACCEPTED).build();
			} catch (IOException e) {
				LOG.info(e.getMessage());
				e.printStackTrace();
				throw new UserException("user not created");
			}

		} else
			throw new UserException("please select a image file");

	}

}
