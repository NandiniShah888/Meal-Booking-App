package com.project.MealBooking.Controller;


import com.project.MealBooking.Service.AuthenticationService;
import com.project.MealBooking.config.JwtService;
import com.project.MealBooking.dto.AuthenticationReponse;
import com.project.MealBooking.dto.AuthenticationRequest;
import com.project.MealBooking.dto.ChangePasswordRequest;
import com.project.MealBooking.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mealBooking/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtService jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationReponse> register(
            @RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationReponse> login(
            @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            authenticationService.changePassword(request);
            return ResponseEntity.ok("Password changed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
