package service;

import dto.CartHistoryItemDTO;
import dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ICartHistoryItemService {
    List<CartHistoryItemDTO> getAllCartHistoryItems();
    Optional<CartHistoryItemDTO> getCartHistoryItemById(Long id);
    CartHistoryItemDTO createCartHistoryItem(CartHistoryItemDTO cartHistoryItemDTO, ProductDTO productDTO);
    Optional<CartHistoryItemDTO> updateCartHistoryItem(Long id, CartHistoryItemDTO cartHistoryItemDTO, ProductDTO productDTO);
    void deleteCartHistoryItem(Long id);
}
