package sample;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ResourceApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(ResourceApplication.class, args);
	}

	private String message = "Hello world!";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Map<String, String> home() {
		return Collections.singletonMap("message", message);
	}

	@PreAuthorize("#oauth2.hasAnyScope('read')")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Map<String, String> user(Principal user) {
		return Collections.singletonMap("message", "user is: " + user.toString());
	}

}
