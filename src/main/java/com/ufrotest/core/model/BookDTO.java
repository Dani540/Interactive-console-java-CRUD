package com.ufrotest.core.model;

import com.ufrotest.constants.Categories;

import java.util.List;

public record BookDTO(int id, String title, String author, String category, int copies, List<Integer> ratings, List<String> comments)
implements IEntityDTO{
}
