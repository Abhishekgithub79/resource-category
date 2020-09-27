package com.tesla.resource_category.service.iservice

import com.tesla.resource_category.model.SubSubCategoryRequestDTO
import com.tesla.resource_category.model.SubSubCategoryResponseDTO

interface ISubSubCategoryService {
    fun getAll(subCategoryId: Long, active: Boolean): List<SubSubCategoryResponseDTO>

    fun save(request: SubSubCategoryRequestDTO): SubSubCategoryResponseDTO

    fun disable(subCategoryId: Long)
}