package persistence;

import model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart,Long> {
}
