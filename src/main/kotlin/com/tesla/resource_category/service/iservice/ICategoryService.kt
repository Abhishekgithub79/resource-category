package com.tesla.resource_category.service.iservice

import com.tesla.resource_category.model.CategoryRequestDTO
import com.tesla.resource_category.model.CategoryResponseDTO

interface ICategoryService {
    fun getAll(active: Boolean): List<CategoryResponseDTO>

    fun save(request: CategoryRequestDTO): CategoryResponseDTO

    fun disable(categoryId: Long)
}