package persistence;
import model.CartHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartHistoryRepository extends JpaRepository<CartHistory,Long> {
}
