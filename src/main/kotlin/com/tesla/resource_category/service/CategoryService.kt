package com.tesla.resource_category.service

import com.tesla.resource_category.entity.CategoryEntity
import com.tesla.resource_category.exception.ErrorMessage
import com.tesla.resource_category.exception.custom_exceptions.BadRequestException
import com.tesla.resource_category.model.CategoryRequestDTO
import com.tesla.resource_category.model.CategoryResponseDTO
import com.tesla.resource_category.repo.CategoryRepository
import com.tesla.resource_category.service.iservice.ICategoryService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException

@Service
class CategoryService: ICategoryService {
    companion object {
        private val log = LoggerFactory.getLogger(CategoryService::class.java)
    }

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    override fun getAll(active: Boolean): List<CategoryResponseDTO> {
        val result = categoryRepository.getAll(active)
        val response = mutableListOf<CategoryResponseDTO>()

        for (item in result) {
            response.add(getResponseDTO(item))
        }

        return response
    }

    override fun save(request: CategoryRequestDTO): CategoryResponseDTO {
        var category: CategoryEntity
        val timestamp = LocalDateTime.now(Clock.systemUTC())

        if (request.id != null) {
            category = validateCategory(request.id)

            category.name = request.name
            category.imageUrl = request.imageUrl
            category.active = request.active
            category.updatedBy = request.user
            category.updatedAt = timestamp
        } else {
            category = CategoryEntity(
                    name = request.name,
                    imageUrl = request.imageUrl,
                    active = request.active,
                    createdAt = timestamp,
                    updatedAt = timestamp,
                    createdBy = request.user,
                    updatedBy = request.user
            )
        }

        category = categoryRepository.save(category)
        return getResponseDTO(category)
    }

    override fun disable(categoryId: Long) {
        val category = validateCategory(categoryId)

        if (!category.active)
            throw BadRequestException(ErrorMessage.BAD_REQUEST, "category already disabled")

        category.active = false
        category.updatedAt = LocalDateTime.now(Clock.systemUTC())

        categoryRepository.save(category)
    }

    private fun validateCategory(categoryId: Long): CategoryEntity {
        val category: CategoryEntity
        try {
            category = categoryRepository.getOne(categoryId)
        } catch (e: EntityNotFoundException) {
            throw BadRequestException(ErrorMessage.BAD_REQUEST, "category not found")
        }

        return category
    }

    private fun getResponseDTO(payload: CategoryEntity): CategoryResponseDTO {
        return CategoryResponseDTO(
                id = payload.id!!,
                name = payload.name,
                imageUrl = payload.imageUrl,
                active = payload.active
        )
    }
}