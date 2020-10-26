package com.scholar.security.config;

import java.util.HashMap;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class JwtCustomClaimsTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, 
			OAuth2Authentication authentication) {
		
		if(authentication.getPrincipal() instanceof AuthUser) {
			AuthUser authUser = (AuthUser) authentication.getPrincipal();
			
			HashMap<String, Object> info = new HashMap<String, Object>();
			info.put("person_name", authUser.getName());
			info.put("person_id", authUser.getId());
			
			DefaultOAuth2AccessToken oAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
			oAuth2AccessToken.setAdditionalInformation(info);
		}
		
		return accessToken;
	}

}
