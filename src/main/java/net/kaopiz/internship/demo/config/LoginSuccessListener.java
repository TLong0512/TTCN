package net.kaopiz.internship.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import net.kaopiz.internship.demo.entity.User;
import net.kaopiz.internship.demo.repository.IUserRepository;

@AllArgsConstructor
@Component
public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	private final IUserRepository userRepository;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {

		UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
		User user = userRepository.findByEmail(userDetails.getUsername()).orElse(null);

		if (user != null) {
			// Increment login count
			user.setLogInCount(user.getLogInCount() + (long) 1);
			userRepository.save(user);
		}
	}

}
