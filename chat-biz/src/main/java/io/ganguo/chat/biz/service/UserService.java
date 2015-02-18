package io.ganguo.chat.biz.service;

import io.ganguo.chat.biz.entity.User;
import io.ganguo.chat.biz.entity.UserDetail;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	public boolean login(User user) {
		if (user.getPassword().equals("test")) {
			user.setAuthToken("token_ok");
			return true;
		}
		user.setAuthToken(null);
		return false;
	}

	public User loadUser(User user) {
		user.setNickName("Tony");
		return user;
	}

	public User getUserById(long id) {
		User user = new User();
		user.setId(id);

		return user;
	}

	public UserDetail getUserDetailById(long id) {
		UserDetail detail = new UserDetail();
		detail.setId(id);

		return detail;
	}

}
