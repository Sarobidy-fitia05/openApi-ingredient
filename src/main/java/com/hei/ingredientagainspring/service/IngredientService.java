package com.hei.ingredientagainspring.service;

import com.hei.ingredientagainspring.entity.Ingredient;
import com.hei.ingredientagainspring.entity.StockValue;
import com.hei.ingredientagainspring.entity.Unit;
import com.hei.ingredientagainspring.exception.NotFoundException;
import com.hei.ingredientagainspring.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient getById(Integer id) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);
        if (optionalIngredient.isEmpty()) {
            throw new NotFoundException("Ingredient.id=" + id + " is not found");
        }
        return optionalIngredient.get();
    }

    public StockValue getStockValueAt(Integer ingredientId, Instant temporal, Unit unit) {
        Ingredient ingredient = getById(ingredientId);
        return ingredient.getStockValueAt(temporal, unit);
    }
}
