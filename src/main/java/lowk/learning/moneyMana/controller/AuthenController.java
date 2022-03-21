package lowk.learning.moneyMana.controller;

import lombok.extern.slf4j.Slf4j;
import lowk.learning.moneyMana.contanst.Constant;
import lowk.learning.moneyMana.dto.AuthenResponseDTO;
import lowk.learning.moneyMana.dto.AuthenRequestDTO;
import lowk.learning.moneyMana.service.AuthenService;
import lowk.learning.moneyMana.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/auth")
@Slf4j
public class AuthenController {

    @Autowired
    private AuthenService authenService;

    @PostMapping("/login")
    public ResponseEntity<Msg> login(@Valid @RequestBody AuthenRequestDTO req){
        return new Msg(Constant.SUCCESS, "").done();
    }

    @PostMapping("/register")
    public ResponseEntity<Msg> register(@Valid @RequestBody AuthenRequestDTO req){
        return authenService.register(req).done();
    }

    @PostMapping("/forget")
    public void forget(@RequestBody AuthenResponseDTO body){
    }

    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody AuthenResponseDTO body){
    }
}
