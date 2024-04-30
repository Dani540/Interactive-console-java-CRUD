package com.ufrotest.model;

import com.ufrotest.utils.Categories;

import java.util.List;

public record BookDTO(String title, String author, Categories category, int amount, List<Integer> ratings, List<String> comments) {}
