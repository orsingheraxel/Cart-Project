package dto;

import dto.CartItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CartDTO {
    private Long id;
    private Long userId;
    private List<CartItemDTO> cartItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
