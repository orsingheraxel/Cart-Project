package service;

import dto.CartDTO;
import dto.UserEntityDTO;

import java.util.List;
import java.util.Optional;

public interface ICartService {
    List<CartDTO> getAllCarts();
    Optional<CartDTO> getCartById(Long id);
    CartDTO createCart(CartDTO cartDTO, UserEntityDTO user);
    Optional<CartDTO> updateCart(Long id, CartDTO cartDTO, UserEntityDTO user);
    void deleteCart(Long id);
}
