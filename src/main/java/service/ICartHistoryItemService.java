package service;

import dto.CartHistoryItemDTO;
import dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ICartHistoryItemService {
    List<CartHistoryItemDTO> getAllCartHistoryItems();
    Optional<CartHistoryItemDTO> getCartHistoryItemById(Long id);
    CartHistoryItemDTO createCartHistoryItem(CartHistoryItemDTO cartHistoryItemDTO, Long productId);
    Optional<CartHistoryItemDTO> updateCartHistoryItem(Long id, CartHistoryItemDTO cartHistoryItemDTO, Long productId);
    void deleteCartHistoryItem(Long id);
}
