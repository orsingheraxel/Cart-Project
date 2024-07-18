package service.impl;

import dto.CartDTO;

import dto.UserEntityDTO;
import mapper.CartMapper;
import model.Cart;
import model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.ICartRepository;
import persistence.IUserEntityRepository;
import service.ICartService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IUserEntityRepository userRepository;

    @Override
    public List<CartDTO> getAllCarts() {
        return cartRepository.findAll().stream()
                .map(CartMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CartDTO> getCartById(Long id) {
        return cartRepository.findById(id)
                .map(CartMapper::toDTO);
    }

    @Override
    public CartDTO createCart(CartDTO cartDTO, Long userId) {
        Cart cart = CartMapper.toEntity(cartDTO, userId);
        Cart savedCart = cartRepository.save(cart);
        return CartMapper.toDTO(savedCart);
    }

    @Override
    public Optional<CartDTO> updateCart(Long id, CartDTO cartDTO, Long userId) {
        if (!cartRepository.existsById(id)) {
            return Optional.empty();
        }
        Cart cart = CartMapper.toEntity(cartDTO, userId);
        cart.setId(id);
        Cart updatedCart = cartRepository.save(cart);
        return Optional.of(CartMapper.toDTO(updatedCart));
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
