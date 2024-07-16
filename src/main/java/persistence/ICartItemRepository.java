package persistence;

import model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartItemRepository extends JpaRepository<CartItem,Long> {
}
