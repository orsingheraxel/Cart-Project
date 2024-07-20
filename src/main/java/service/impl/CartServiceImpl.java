package service.impl;

import dto.CartDTO;
import dto.CartItemDTO;
import mapper.CartMapper;
import model.Cart;
import model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.ICartRepository;
import persistence.ICartItemRepository;
import service.ICartService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private ICartItemRepository cartItemRepository;

    @Override
    public List<CartDTO> getAllCarts() {
        return cartRepository.findAll().stream()
                .map(cart -> {
                    CartDTO cartDTO = CartMapper.toDTO(cart);
                    List<CartItemDTO> cartItemDTOs = cart.getCartItems().stream()
                            .map(CartMapper::toCartItemDTO)
                            .collect(Collectors.toList());
                    cartDTO.setCartItems(cartItemDTOs);
                    return cartDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CartDTO> getCartById(Long id) {
        return cartRepository.findById(id)
                .map(cart -> {
                    CartDTO cartDTO = CartMapper.toDTO(cart);
                    List<CartItemDTO> cartItemDTOs = cart.getCartItems().stream()
                            .map(CartMapper::toCartItemDTO)
                            .collect(Collectors.toList());
                    cartDTO.setCartItems(cartItemDTOs);
                    return cartDTO;
                });
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
