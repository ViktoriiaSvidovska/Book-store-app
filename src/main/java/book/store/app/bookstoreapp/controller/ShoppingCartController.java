package book.store.app.bookstoreapp.controller;

import book.store.app.bookstoreapp.dto.shoppingcart.ShoppingCartResponseDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
@Tag(name = "ShoppingCart", description = "Managing shopping carts")
@ApiResponse(content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = ShoppingCartResponseDto.class))})
public class ShoppingCartController {
}
