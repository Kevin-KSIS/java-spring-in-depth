package lowk.learning.moneyMana.repository;

import lowk.learning.moneyMana.model.UserDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDAO, Integer> {
    boolean existsByUsername(String email);
    Optional<UserDAO> findByUsername(String username);
    boolean existsByRoles(String role);
}
