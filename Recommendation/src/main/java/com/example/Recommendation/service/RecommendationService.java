

package com.example.Recommendation.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Recommendation.model.ProductCatalogDTO;

@Service
public class RecommendationService {

    @Autowired
    private RestTemplate restTemplate;

    private final String CATALOG_CATEGORY_URL =
            "http://localhost:8050/catalogs/products/category/";

    public List<ProductCatalogDTO> getByCategory(List<String> categories) {

        List<ProductCatalogDTO> recommendations = new ArrayList<>();

        for (String category : categories) {

            try {
                ProductCatalogDTO[] products =
                        restTemplate.getForObject(
                                CATALOG_CATEGORY_URL + category,
                                ProductCatalogDTO[].class
                        );

                if (products != null) {
                    recommendations.addAll(Arrays.asList(products));
                }

            } catch (Exception e) {
                System.out.println("Error fetching category: " + category);
            }
        }

        return recommendations;
    }
}