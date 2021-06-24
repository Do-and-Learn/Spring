package idv.teddy.repository;

import idv.teddy.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDetailsRepository extends CrudRepository<User, Long> {
    User findByUsername(String name);
}
