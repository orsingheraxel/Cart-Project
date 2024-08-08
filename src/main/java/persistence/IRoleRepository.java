package persistence;

import model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity,Long> {

    //trae los que existen de los que le paso
    List<RoleEntity> findRoleEntitiesByRoleEnumIn(List<String> roleNames);
}
