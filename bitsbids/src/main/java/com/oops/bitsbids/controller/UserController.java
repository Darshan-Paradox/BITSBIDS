package com.oops.bitsbids.controller;

import com.oops.bitsbids.model.User;
import com.oops.bitsbids.repository.UserRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;

import jakarta.servlet.http.HttpServletRequest;

import com.oops.bitsbids.model.User;
import com.oops.bitsbids.repository.UserRepository;

import java.util.*;

@RestController
@RequestMapping("/api")
public class UserController {

	private final ClientRegistration registration;

	@Autowired
	private UserRepository userRepository;

    public UserController(ClientRegistrationRepository registrations) {
        this.registration = registrations.findByRegistrationId("google");
    }

    @CrossOrigin
    @GetMapping("/user")
    public ResponseEntity<?> getLoggedInUser(@AuthenticationPrincipal OidcUser user) {
        String email = user.getEmail();
        String name = user.getFullName();
        String icon = user.getPicture();

        if (user.getEmailVerified()) {
            if (userRepository.findByEmail(email) == null) {
                userRepository.save(new User(name, email, icon));
            }
            return new ResponseEntity<>(userRepository.findByEmail(email), HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
    }

    /*
	@PostMapping("/api/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, @AuthenticationPrincipal(expression = "idToken") OidcIdToken idToken) {

        String logoutUrl = this.registration.getProviderDetails().getConfigurationMetadata().get("end_session_endpoint").toString();

        Map<String, String> logoutDetails = new HashMap<>();

        logoutDetails.put("logoutUrl", logoutUrl);
        logoutDetails.put("idToken", idToken.getTokenValue());

        request.getSession(false).invalidate();

        return ResponseEntity.ok().body(logoutDetails);
    }
    */

}
