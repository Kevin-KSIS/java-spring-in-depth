package lowk.learning.moneyMana.service;

import lowk.learning.moneyMana.model.UserDAO;
import lowk.learning.moneyMana.model.UserPrinciple;
import lowk.learning.moneyMana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserPrinciple loadUserByUsername(String username) throws UsernameNotFoundException {
        // @todo: test SSTI
        UserDAO u = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return new UserPrinciple(u);
    }
}
