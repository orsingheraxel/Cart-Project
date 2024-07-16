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

        Product product = cartHistoryItem.getProduct();

        CartHistoryItemDTO cartHistoryItemDTO = new CartHistoryItemDTO();
        cartHistoryItemDTO.setId(cartHistoryItem.getId());
        cartHistoryItemDTO.setProductId(product.getId());
        cartHistoryItemDTO.setQuantity(cartHistoryItem.getQuantity());
        cartHistoryItemDTO.setPriceAtPurchase(cartHistoryItem.getPriceAtPurchase());
        cartHistoryItemDTO.setCreatedAt(cartHistoryItem.getCreatedAt());
        return cartHistoryItemDTO;
    }

    public static CartHistoryItem toEntity(CartHistoryItemDTO cartHistoryItemDTO, ProductDTO productDTO) {
        if (cartHistoryItemDTO == null) {
            return null;
        }

        Product product = ProductMapper.toEntity(productDTO);

        CartHistoryItem cartHistoryItem = new CartHistoryItem();
        cartHistoryItem.setId(cartHistoryItemDTO.getId());
        cartHistoryItem.setProduct(product);
        cartHistoryItem.setQuantity(cartHistoryItemDTO.getQuantity());
        cartHistoryItem.setPriceAtPurchase(cartHistoryItemDTO.getPriceAtPurchase());
        cartHistoryItem.setCreatedAt(cartHistoryItemDTO.getCreatedAt());
        return cartHistoryItem;
    }
}
