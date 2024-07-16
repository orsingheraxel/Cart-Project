package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartHistoryDTO {
    private Long id;
    private Long userId;
    private Double totalPrice;
    private List<CartHistoryItemDTO> cartHistoryItems;
    private LocalDateTime createdAt;
}
