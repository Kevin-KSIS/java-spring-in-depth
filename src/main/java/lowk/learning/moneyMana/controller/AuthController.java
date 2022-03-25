package lowk.learning.moneyMana.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lowk.learning.moneyMana.config.JwtTokenProvider;
import lowk.learning.moneyMana.dto.AuthenRequestDTO;
import lowk.learning.moneyMana.service.AuthService;
import lowk.learning.moneyMana.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/auth")
@Slf4j
@AllArgsConstructor
public class AuthController {

    private AuthService authService;
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<Msg> login(@RequestBody AuthenRequestDTO req){
        return authService.login(req).done();
    }

    @PostMapping("/register")
    public ResponseEntity<Msg> register(@Valid @RequestBody AuthenRequestDTO req){
        return authService.register(req).done();
    }

    @GetMapping("/logout")
    public ResponseEntity<Msg> logout(@RequestHeader(value = "Authorization") String bearerString){
        String jwtString = jwtTokenProvider.parse(bearerString);
        return authService.logout(jwtString).done();
    }

    @PostMapping("/forget")
    public void forget(@RequestBody AuthenRequestDTO req){
    }

    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody AuthenRequestDTO req){
    }
}
