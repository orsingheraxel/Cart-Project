package controller;

import dto.CartHistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ICartHistoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cartHistories")
public class CartHistoryController {

    @Autowired
    private ICartHistoryService cartHistoryService;

    @GetMapping
    public ResponseEntity<List<CartHistoryDTO>> getAllCartHistories() {
        List<CartHistoryDTO> cartHistories = cartHistoryService.getAllCartHistories();
        return ResponseEntity.ok(cartHistories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartHistoryDTO> getCartHistoryById(@PathVariable Long id) {
        Optional<CartHistoryDTO> cartHistory = cartHistoryService.getCartHistoryById(id);
        return cartHistory.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CartHistoryDTO> createCartHistory(@RequestBody CartHistoryDTO cartHistoryDTO) {
        CartHistoryDTO createdCartHistory = cartHistoryService.createCartHistory(cartHistoryDTO, cartHistoryDTO.getUserId());
        return ResponseEntity.ok(createdCartHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartHistoryDTO> updateCartHistory(@PathVariable Long id, @RequestBody CartHistoryDTO cartHistoryDTO) {
        Optional<CartHistoryDTO> updatedCartHistory = cartHistoryService.updateCartHistory(id, cartHistoryDTO, cartHistoryDTO.getUserId());
        return updatedCartHistory.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartHistory(@PathVariable Long id) {
        cartHistoryService.deleteCartHistory(id);
        return ResponseEntity.noContent().build();
    }
}
