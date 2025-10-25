package com.Atiq.Springproject.RestController;


import com.Atiq.Springproject.Entity.AuthenticationResponse;
import com.Atiq.Springproject.Entity.User;
import com.Atiq.Springproject.Service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticalController {



    @Autowired
    private AuthService authService;




    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }


    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.registerAdmin(request));
    }

    @PostMapping("/register/pharmacist")
    public ResponseEntity<AuthenticationResponse> registerPharmacist(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.registerPharmacist(request));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }


    @GetMapping("/activate/{id}")
    public ResponseEntity<String> activateUser(@PathVariable("id") int id) {
        String response = authService.activateUser(id);
        return ResponseEntity.ok(response);
    }


}
