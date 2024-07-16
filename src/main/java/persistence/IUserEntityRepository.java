package persistence;

import model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserEntityRepository extends JpaRepository<UserEntity,Long> {
}
