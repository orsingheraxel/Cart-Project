package service;

import dto.CartHistoryDTO;
import dto.UserEntityDTO;
import model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface ICartHistoryService {
    List<CartHistoryDTO> getAllCartHistories();
    Optional<CartHistoryDTO> getCartHistoryById(Long id);
    CartHistoryDTO createCartHistory(CartHistoryDTO cartHistoryDTO, UserEntityDTO userEntity);
    Optional<CartHistoryDTO> updateCartHistory(Long id, CartHistoryDTO cartHistoryDTO, UserEntityDTO userEntity);
    void deleteCartHistory(Long id);
}
