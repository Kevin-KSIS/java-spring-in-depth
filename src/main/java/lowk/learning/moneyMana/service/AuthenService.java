package lowk.learning.moneyMana.service;

import lowk.learning.moneyMana.contanst.Constant;
import lowk.learning.moneyMana.dto.AuthenResponseDTO;
import lowk.learning.moneyMana.dto.AuthenRequestDTO;
import lowk.learning.moneyMana.model.UserDAO;
import lowk.learning.moneyMana.repository.UserRepository;
import lowk.learning.moneyMana.util.Msg;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserDAO fromUserDTO(AuthenRequestDTO u){
        return modelMapper.map(u, UserDAO.class);
    }

    private AuthenResponseDTO toUserDTO(UserDAO u){
        return modelMapper.map(u, AuthenResponseDTO.class);
    }

//    public Msg login(AuthenRequestDTO req){
//        UserDAO userReq = fromUserDTO(req);
//
//        if (! userRepository.existsUserByEmail(userReq.getEmail())){
//            return new Msg(Constant.FAIL, "Login Failure");
//        }
//
//        UserDAO userDB = userRepository.findByEmail(userReq.getEmail());
//
//        return new Msg(Constant.SUCCESS, "Login Successful");
//    }
//
    public Msg register(AuthenRequestDTO req){
        UserDAO user = modelMapper.map(req, UserDAO.class);

        // Just one AUTHOR user
        if (userRepository.existsByRoles("AUTHOR")){
            user.setRoles("GUEST");
        }else{
            user.setRoles("AUTHOR");
        }

        if (userRepository.existsByUsername(user.getUsername())){
            return new Msg(Constant.FAIL, "User is exists");
        }
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        userRepository.save(user);
        return new Msg(Constant.SUCCESS, "Register successful");
    }
}
