//@author Bozzay, Ádám
package hu.kleatech.bead1.service;

import hu.kleatech.bead1.dao.UserRepository;
import hu.kleatech.bead1.model.User;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User addUser(String name, String uniqueAlias, String passwd) {
		if (userRepository.findByUniqueAlias(uniqueAlias)!=null) return null;
		return userRepository.save(new User(name, uniqueAlias, passwd));
	}

	public void removeUser(User user) {
		userRepository.delete(user);
	}

	public User getUser(String uniqueAlias) {
		return userRepository.findByUniqueAlias(uniqueAlias);
	}

	public boolean changePasswd(User user, String newpass) {
		if (Objects.equals(user.getPasswd(), newpass)) return false;
		user.setPasswd(newpass);
		userRepository.save(user);
		return true;
	}
}
