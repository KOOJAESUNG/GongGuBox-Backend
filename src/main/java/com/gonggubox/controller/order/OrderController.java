package com.gonggubox.controller.order;

import com.gonggubox.config.spring_security.auth.PrincipalDetails;
import com.gonggubox.dto.order.OrderDto;
import com.gonggubox.service.order.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal PrincipalDetails principalDetails, @Valid OrderDto.OrderPostDto orderPostDto) {
        return ResponseEntity.ok().body(orderService.createOrder(principalDetails.getUser().getId(), orderPostDto));
    }

    @GetMapping("/getOrder")
    public ResponseEntity<?> getOrder(@RequestParam @NotNull @Min(1) Long orderId) {
        return ResponseEntity.ok().body(orderService.getOrder(orderId));
    }

    @DeleteMapping("/deleteOrder")
    public ResponseEntity<?> deleteOrder(@RequestParam @NotNull @Min(1) Long orderId) {
        return ResponseEntity.ok().body(orderService.deleteOrder(orderId));
    }

}
