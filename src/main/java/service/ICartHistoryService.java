package service;

import dto.CartHistoryDTO;

import java.util.List;
import java.util.Optional;

public interface ICartHistoryService {
    List<CartHistoryDTO> getAllCartHistories();
    Optional<CartHistoryDTO> getCartHistoryById(Long id);
    CartHistoryDTO createCartHistory(CartHistoryDTO cartHistoryDTO, Long userId);
    Optional<CartHistoryDTO> updateCartHistory(Long id, CartHistoryDTO cartHistoryDTO, Long userId);
    void deleteCartHistory(Long id);
}
