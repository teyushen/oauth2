package sample;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer
public class AuthorizationApplication {
	private static final Log log = LogFactory.getLog(AuthorizationApplication.class);

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(AuthorizationApplication.class, args);
	}

	@RequestMapping("/user")
    public Principal user(Principal user) {
        log.debug("user: " + user.toString());
        return user;
    }

}
