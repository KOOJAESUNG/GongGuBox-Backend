package com.gonggubox.controller.cart;

import com.gonggubox.config.spring_security.auth.PrincipalDetails;
import com.gonggubox.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/getCart")
    public ResponseEntity<?> getCart(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok().body(cartService.getCart(principalDetails.getUser().getId()));
    }

    @PostMapping("/addItemToCart")
    public ResponseEntity<?> addItemToCart(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam Long itemId, @RequestParam Integer count) {
        return ResponseEntity.ok().body(cartService.addItemToCart(principalDetails.getUser().getId(), itemId, count));
    }

    @DeleteMapping("/deleteItemInCart")
    public ResponseEntity<?> deleteItemInCart(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam Long itemId) {
        return ResponseEntity.ok().body(cartService.deleteItemInCart(principalDetails.getUser().getId(), itemId));
    }

    @PatchMapping("/updateItemCountInCart")
    public ResponseEntity<?> updateItemCountInCart(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam Long itemId, @RequestParam Integer count) {
        return ResponseEntity.ok().body(cartService.updateItemCountInCart(principalDetails.getUser().getId(), itemId, count));
    }

    @PatchMapping("/clearCart")
    public ResponseEntity<?> clearCart(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok().body(cartService.clearCart(principalDetails.getUser().getId()));
    }
}
