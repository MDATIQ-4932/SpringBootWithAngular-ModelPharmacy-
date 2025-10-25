package com.Atiq.Springproject.Service;


import com.Atiq.Springproject.Entity.AuthenticationResponse;
import com.Atiq.Springproject.Entity.Role;
import com.Atiq.Springproject.Entity.Token;
import com.Atiq.Springproject.Entity.User;
import com.Atiq.Springproject.Repository.ITokenRepository;
import com.Atiq.Springproject.Repository.IUserRepository;
import com.Atiq.Springproject.jwt.JwtService;
import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthService  {


    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ITokenRepository iTokenRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmailService emailService;

    @Autowired  // optional if only one constructor
    public AuthService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public AuthService() {
    }





    private void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);

        iTokenRepository.save(token);
    }


    private void revokeAllTokenByUser(User user) {

        List<Token> validTokens = iTokenRepository.findAllTokensByUser(user.getId());
        if(validTokens.isEmpty()) {
            return;
        }

        // Set all valid tokens for the user to logged out
        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        // Save the changes to the tokens in the token repository
        iTokenRepository.saveAll(validTokens);
    }

    public AuthenticationResponse register(User user) {

        // Check if the user already exists
        if(iUserRepository.findByEmail(user.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, "User already exists");
        }

        // Create a new user entity and save it to the database
//        User user = new User();
//        user.setName(user.getName());
//        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
        user.setRole(Role.valueOf("USER"));
//        user.setLock(true);
        user.setActive(true);
        iUserRepository.save(user);

        // Generate JWT token for the newly registered user
        String jwt = jwtService.generateToken(user);

        // Save the token to the token repository
        saveUserToken(jwt, user);
        sendActivationEmail(user);

        return new AuthenticationResponse(jwt , "User registration was successful");
    }

    public AuthenticationResponse registerAdmin(User user) {

        // Check if the user already exists
        if (iUserRepository.findByEmail(user.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, "User already exists");
        }

        // Create a new user entity and save it to the database

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.valueOf("ADMIN"));
//        user.setLock(true);
        user.setActive(false);

        iUserRepository.save(user);

        // Generate JWT token for the newly registered user
        String jwt = jwtService.generateToken(user);

        // Save the token to the token repository
        saveUserToken(jwt, user);
        sendActivationEmail(user);

        return new AuthenticationResponse(jwt, "User registration was successful");
    }

    public AuthenticationResponse registerPharmacist(User user) {

        // Check if the user already exists
        if (iUserRepository.findByEmail(user.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, "User already exists");
        }

        // Create a new user entity and save it to the database

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.valueOf("PHARMACIST"));
//        user.setLock(false);
        user.setActive(false);

        iUserRepository.save(user);

        // Generate JWT token for the newly registered user
        String jwt = jwtService.generateToken(user);

        // Save the token to the token repository
        saveUserToken(jwt, user);
        sendActivationEmail(user);

        return new AuthenticationResponse(jwt, "User registration was successful");
    }

    // Method to authenticate a user
    public AuthenticationResponse authenticate(User request) {

        // Authenticate user credentials using Spring Security's AuthenticationManager
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // Retrieve the user from the database
        User user = iUserRepository.findByEmail(request.getUsername()).orElseThrow();

        // Generate JWT token for the authenticated user
        String jwt = jwtService.generateToken(user);

        // Revoke all existing tokens for this user
        revokeAllTokenByUser(user);

        // Save the new token to the token repository
        saveUserToken(jwt, user);


        return new AuthenticationResponse(jwt, "User login was successful");
    }


    private void sendActivationEmail(User user) {
        String subject = "Welcome to Our Service – Confirm Your Registration";

        String activationLink="http://localhost:8085/api/activate/"+user.getId();

        String mailText = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<style>"
                + "  body { font-family: Arial, sans-serif; line-height: 1.6; }"
                + "  .container { max-width: 600px; margin: auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 10px; }"
                + "  .header { background-color: #4CAF50; color: white; padding: 10px; text-align: center; border-radius: 10px 10px 0 0; }"
                + "  .content { padding: 20px; }"
                + "  .footer { font-size: 0.9em; color: #777; margin-top: 20px; text-align: center; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "  <div class='container'>"
                + "    <div class='header'>"
                + "      <h2>Welcome to Our Platform</h2>"
                + "    </div>"
                + "    <div class='content'>"
                + "      <p>Dear " + user.getName() + ",</p>"
                + "      <p>Thank you for registering with us. We are excited to have you on board!</p>"
                + "      <p>Please confirm your email address to activate your account and get started.</p>"
                + "      <p>If you have any questions or need help, feel free to reach out to our support team.</p>"
                + "      <br>"
                + "      <p>Best regards,<br>The Support Team</p>"
                + "      <p>To Activate Your Account, please click the following link:</p>"
                + "      <p><a href=\"" + activationLink + "\">Activate Account</a></p>"
                + "    </div>"
                + "    <div class='footer'>"
                + "      &copy; " + java.time.Year.now() + " YourCompany. All rights reserved."
                + "    </div>"
                + "  </div>"
                + "</body>"
                + "</html>";

        emailService.sendSimpleEmail(user.getEmail(), subject, mailText);
    }
    // Activate user based on the token
    public String activateUser(long id) {

        User user = iUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not Found with this ID"));

        if (user != null) {

            user.setActive(true);
            //  user.setActivationToken(null); // Clear token after activation
            iUserRepository.save(user);
            return "User activated successfully!";
        } else {
            return "Invalid activation token!";
        }
    }

}
