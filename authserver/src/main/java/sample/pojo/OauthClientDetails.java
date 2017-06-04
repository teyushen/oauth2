package sample.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.oauth2.provider.client.BaseClientDetails;

@Entity
public class OauthClientDetails extends BaseClientDetails {
	private static final long serialVersionUID = -8290003991325296682L;

	@Id
	@Column(length = 256)
	private String clientId;
	
	@Column(length = 256)
	private String resourceIds;
	
	@Column(length = 256)
    private String clientSecret;

	@Column(length = 256)
    private String scope;
	
	@Column(length = 256)
    private String authorizedGrantTypes;
	
	@Column(length = 256)
	private String webServerRedirectUri;

	@Column(length = 256)
    private String authorities;
	
    @Column(length = 4096)
    private String additionalInformation;
    
    @Column(length = 256)
    private String autoapprove;
    
}

