package lowk.learning.moneyMana.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lowk.learning.moneyMana.config.JwtTokenProvider;
import lowk.learning.moneyMana.contanst.Constant;
import lowk.learning.moneyMana.dto.AuthenResponseDTO;
import lowk.learning.moneyMana.dto.AuthenRequestDTO;
import lowk.learning.moneyMana.model.User;
import lowk.learning.moneyMana.model.UserPrinciple;
import lowk.learning.moneyMana.repository.UserRepository;
import lowk.learning.moneyMana.util.Msg;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Slf4j
@AllArgsConstructor
@Service
public class AuthService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authManager;
    private JwtTokenProvider jwtTokenProvider;
    private InMemoryTokenRepositoryImpl inMemTokenRepository;

    private User fromUserDTO(AuthenRequestDTO u){
        return modelMapper.map(u, User.class);
    }

    private AuthenResponseDTO toUserDTO(User u){
        return modelMapper.map(u, AuthenResponseDTO.class);
    }

    public Msg login(AuthenRequestDTO req){
        User userReq = fromUserDTO(req);
        try{

            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userReq.getUsername(), userReq.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            String jwtToken = jwtTokenProvider.generateToken(
                    (UserPrinciple) auth.getPrincipal()
            );
            log.info(userReq.getUsername(), "login successful");
            return new Msg(Constant.SUCCESS, "Login successful", jwtToken);
        }catch (Exception e){
            log.error(userReq.getUsername(), "login fail");
            return new Msg(Constant.FAIL, "Login fail");
        }
    }

    public Msg register(AuthenRequestDTO req){
        // SECURITY: avoid IDOR
        req.setId(0);
        User user = modelMapper.map(req, User.class);

        if (userRepository.existsByRoles(Constant.ROLE_ADMIN) || !StringUtils.hasText(user.getRoles())){
            // Just one AUTHOR user and default is GUEST user
            user.setRoles(Constant.ROLE_GUEST);
        }

        if (userRepository.existsByUsername(user.getUsername())){
            return new Msg(Constant.FAIL, "Username is exists");
        }
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        userRepository.save(user);

        // login
        return new Msg(Constant.SUCCESS, "Register successful",  login(req).getData());
    }

    public Msg logout(String jwtString){
        String sessionId = jwtTokenProvider.getSigFromToken(jwtString);
        String username = jwtTokenProvider.getUsernameFromToken(jwtString);


        PersistentRememberMeToken tokenRemoval = new PersistentRememberMeToken(
                username, sessionId,
                jwtString,
                new Date());
        inMemTokenRepository.createNewToken(tokenRemoval);

        return new Msg(Constant.SUCCESS, "Logout successful");
    }
}
