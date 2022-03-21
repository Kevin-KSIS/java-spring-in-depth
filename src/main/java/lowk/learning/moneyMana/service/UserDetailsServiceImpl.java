package lowk.learning.moneyMana.service;

import lowk.learning.moneyMana.model.UserDAO;
import lowk.learning.moneyMana.model.UserModel;
import lowk.learning.moneyMana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserModel loadUserByUsername(String username) throws UsernameNotFoundException {
        // @todo: test SSTI
        UserDAO u = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return new UserModel(u);
    }
}
