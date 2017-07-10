package sample.config;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private Environment environment;

	@Bean
	public JwtAccessTokenConverter jwtTokenEnhancer() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		String pwd = environment.getProperty("keystore.password");
		KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), pwd.toCharArray()).getKeyPair("jwt");
		converter.setKeyPair(keyPair);
		return converter;
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("sample").secret("samplesecret")
				.authorizedGrantTypes("authorization_code", "refresh_token").scopes("read", "delete").and()
				.withClient("sample2").secret("sample2secret").authorizedGrantTypes("implicit").scopes("read");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).accessTokenConverter(jwtTokenEnhancer());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

}
