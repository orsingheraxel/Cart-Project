package service;

import dto.CartDTO;
import dto.UserEntityDTO;

import java.util.List;
import java.util.Optional;

public interface ICartService {
    List<CartDTO> getAllCarts();
    Optional<CartDTO> getCartById(Long id);
    CartDTO createCart(CartDTO cartDTO, Long userId);
    Optional<CartDTO> updateCart(Long cartId, CartDTO cartDTO, Long userId);
    void deleteCart(Long id);
}
