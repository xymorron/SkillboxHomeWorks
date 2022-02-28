package com.example.bookshop.security.oauth;

import com.example.bookshop.data.struct.service.UserService;
import com.example.bookshop.data.struct.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class CustomOidcUserService  extends OidcUserService {

    private final UserService userService;
    private final Logger logger = Logger.getLogger(CustomOidcUserService.class.getName());

    @Autowired
    public CustomOidcUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        updateUser(oidcUser);
        return oidcUser;
    }

    private void updateUser(OidcUser oidcUser) {
        Map<String, Object> attributes = oidcUser.getAttributes();
        String email = (String) attributes.get("email");
        logger.info("update user called for email: " + email);
        UserEntity user = userService.getUserEntityByEmail(email);
        if (user == null) {
            user = new UserEntity();
            user.setEmail(email);
            user.setName((String) attributes.get("name"));
            user.setHash((String) attributes.get("at_hash"));
            user.setBalance(0);
            user.setPassword("");
            user.setRegTime(LocalDateTime.now());
            userService.save(user);
        }
    }


}
