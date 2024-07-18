package mapper;

import dto.CartHistoryItemDTO;
import dto.ProductDTO;
import model.CartHistoryItem;
import model.Product;

public class CartHistoryItemMapper {

    public static CartHistoryItemDTO toDTO(CartHistoryItem cartHistoryItem) {
        if (cartHistoryItem == null) {
            return null;
        }

        CartHistoryItemDTO cartHistoryItemDTO = new CartHistoryItemDTO();
        cartHistoryItemDTO.setId(cartHistoryItem.getId());
        cartHistoryItemDTO.setProductId(cartHistoryItemDTO.getProductId());
        cartHistoryItemDTO.setQuantity(cartHistoryItem.getQuantity());
        cartHistoryItemDTO.setPriceAtPurchase(cartHistoryItem.getPriceAtPurchase());
        cartHistoryItemDTO.setCreatedAt(cartHistoryItem.getCreatedAt());
        return cartHistoryItemDTO;
    }

    public static CartHistoryItem toEntity(CartHistoryItemDTO cartHistoryItemDTO, Long productId) {
        if (cartHistoryItemDTO == null) {
            return null;
        }

        CartHistoryItem cartHistoryItem = new CartHistoryItem();
        cartHistoryItem.setId(cartHistoryItemDTO.getId());
        cartHistoryItem.setProductId(productId);
        cartHistoryItem.setQuantity(cartHistoryItemDTO.getQuantity());
        cartHistoryItem.setPriceAtPurchase(cartHistoryItemDTO.getPriceAtPurchase());
        cartHistoryItem.setCreatedAt(cartHistoryItemDTO.getCreatedAt());
        return cartHistoryItem;
    }
}
