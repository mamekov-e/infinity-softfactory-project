package com.shop.onlineshop.service.api;


import com.shop.onlineshop.dao.UserRepository;
import com.shop.onlineshop.model.GoogleUserInfo;
import com.shop.onlineshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class CustomOidcUserService extends OidcUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        try {
            return processOidcUser(userRequest, oidcUser);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

        private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {
        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());

        // see what other data from userRequest or oidcUser you need

        User userOptional = userRepository.findByEmail(googleUserInfo.getEmail());
        if (userOptional ==null) {
            User user = new User();
            user.setEmail(googleUserInfo.getEmail());
            user.setPassword(passwordEncoder.encode(googleUserInfo.getEmail()));
            user.setFirstName(googleUserInfo.getName());
            user.setRole("USER");
            user.setActive(1);

            // set other needed data

            userRepository.save(user);
        }

        return oidcUser;
    }
}
