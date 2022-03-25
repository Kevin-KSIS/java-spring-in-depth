package lowk.learning.moneyMana.repository;

import lowk.learning.moneyMana.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    boolean existsByUsername(String email);
    Optional<User> findByUsername(String username);
    boolean existsByRoles(String role);
}
