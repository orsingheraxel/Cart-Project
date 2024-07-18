package service.impl;

import dto.CartHistoryDTO;
import dto.UserEntityDTO;
import mapper.CartHistoryMapper;
import model.CartHistory;
import model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.ICartHistoryRepository;
import service.ICartHistoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartHistoryServiceImpl implements ICartHistoryService {
    @Autowired
    private ICartHistoryRepository cartHistoryRepository;

    @Override
    public List<CartHistoryDTO> getAllCartHistories() {
        return cartHistoryRepository.findAll().stream()
                .map(CartHistoryMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<CartHistoryDTO> getCartHistoryById(Long id) {
        return cartHistoryRepository.findById(id)
                .map(CartHistoryMapper::toDTO);
    }

    @Override
    public CartHistoryDTO createCartHistory(CartHistoryDTO cartHistoryDTO, Long userId) {
        CartHistory cartHistory = CartHistoryMapper.toEntity(cartHistoryDTO, userId);
        CartHistory savedCartHistory = cartHistoryRepository.save(cartHistory);
        return CartHistoryMapper.toDTO(savedCartHistory);
    }

    @Override
    public void deleteCartHistory(Long id) {
        cartHistoryRepository.deleteById(id);
    }

    @Override
    public Optional<CartHistoryDTO> updateCartHistory(Long id, CartHistoryDTO cartHistoryDTO, Long userId) {
        if (!cartHistoryRepository.existsById(id)) {
            return Optional.empty();
        }
        CartHistory cartHistory = CartHistoryMapper.toEntity(cartHistoryDTO, userId);
        cartHistory.setId(id);
        CartHistory updatedCartHistory = cartHistoryRepository.save(cartHistory);
        return Optional.of(CartHistoryMapper.toDTO(updatedCartHistory));
    }
}
