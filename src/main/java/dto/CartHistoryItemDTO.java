package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CartHistoryItemDTO {
    private Long id;
    private Long cartHistoryId;
    private Long productId;
    private Integer quantity;
    private Double priceAtPurchase;
    private LocalDateTime createdAt;
}
