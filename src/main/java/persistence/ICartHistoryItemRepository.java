package persistence;

import model.CartHistoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartHistoryItemRepository extends JpaRepository<CartHistoryItem,Long> {
}
