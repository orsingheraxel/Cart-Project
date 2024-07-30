package mapper;

import dto.CartHistoryDTO;
import model.CartHistory;

public class CartHistoryMapper {

    public static CartHistoryDTO toDTO(CartHistory cartHistory) {
        if (cartHistory == null) {
            return null;
        }

        CartHistoryDTO cartHistoryDTO = new CartHistoryDTO();
        cartHistoryDTO.setId(cartHistory.getId());
        cartHistoryDTO.setUserId(cartHistory.getUserId());
        // No se mapea la lista de CartHistoryItem en este ejemplo
        return cartHistoryDTO;
    }

    public static CartHistory toEntity(CartHistoryDTO cartHistoryDTO, Long userId) {
        if (cartHistoryDTO == null) {
            return null;
        }

        CartHistory cartHistory = new CartHistory();
        cartHistory.setId(cartHistoryDTO.getId());
        cartHistory.setUserId(userId);
        // La lista de CartHistoryItem se mapea en el servicio
        return cartHistory;
    }
}
