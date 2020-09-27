package com.tesla.resource_category.service.iservice

import com.tesla.resource_category.model.SubCategoryRequestDTO
import com.tesla.resource_category.model.SubCategoryResponseDTO

interface ISubCategoryService {
    fun getAll(categoryId: Long, active: Boolean): List<SubCategoryResponseDTO>

    fun save(request: SubCategoryRequestDTO): SubCategoryResponseDTO

    fun disable(subCategoryId: Long)
}