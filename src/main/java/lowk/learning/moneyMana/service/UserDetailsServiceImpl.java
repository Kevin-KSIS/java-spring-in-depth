package lowk.learning.moneyMana.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lowk.learning.moneyMana.model.User;
import lowk.learning.moneyMana.model.UserPrinciple;
import lowk.learning.moneyMana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Slf4j
@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService, Serializable {

    private UserRepository userRepository;

    @Override
    public UserPrinciple loadUserByUsername(String username) throws UsernameNotFoundException {
        // @todo: test SSTI
        User u = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return new UserPrinciple(u);
    }
}
