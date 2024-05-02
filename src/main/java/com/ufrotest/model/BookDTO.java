package com.ufrotest.model;

import com.ufrotest.utils.Categories;

import java.util.List;

public record BookDTO(int id, String title, String author, Categories category, int copies, List<Integer> ratings, List<String> comments)
implements IEntityDTO{}
