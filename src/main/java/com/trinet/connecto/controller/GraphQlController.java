package com.trinet.connecto.controller;

import com.trinet.connecto.model.Category;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GraphQlController {
    @QueryMapping
    public List<Category> getCategories(){
        List<String> s = Arrays.stream("".split("")).toList();

        return null;
    }
}
