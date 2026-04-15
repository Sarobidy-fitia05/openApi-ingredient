package com.hei.ingredientagainspring.controller;


import com.hei.ingredientagainspring.dto.DishIngredientRest;
import com.hei.ingredientagainspring.dto.DishRest;
import com.hei.ingredientagainspring.exception.BadRequestException;
import com.hei.ingredientagainspring.exception.NotFoundException;
import com.hei.ingredientagainspring.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class DishController {
    DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/dishes")
    public ResponseEntity<?> getDishes() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(dishService.findAll().stream()
                            .map(dish -> new DishRest(dish.getId(),
                                    dish.getPrice(),
                                    dish.getName(),
                                    dish.getDishIngredients().stream()
                                            .map(dishIngredient ->
                                            {
                                                var ingredient = dishIngredient.getIngredient();
                                                return new DishIngredientRest(ingredient.getId(), ingredient.getName(), ingredient.getCategory(), ingredient.getPrice());
                                            }).toList()
                            ))
                            .toList());
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
