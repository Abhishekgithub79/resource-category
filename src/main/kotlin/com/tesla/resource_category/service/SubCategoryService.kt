package com.tesla.resource_category.service

import com.tesla.resource_category.entity.SubCategoryEntity
import com.tesla.resource_category.exception.ErrorMessage
import com.tesla.resource_category.exception.custom_exceptions.BadRequestException
import com.tesla.resource_category.model.SubCategoryRequestDTO
import com.tesla.resource_category.model.SubCategoryResponseDTO
import com.tesla.resource_category.repo.SubCategoryRepository
import com.tesla.resource_category.service.iservice.ISubCategoryService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException

@Service
class SubCategoryService: ISubCategoryService {
    companion object {
        private val log = LoggerFactory.getLogger(SubCategoryService::class.java)
    }

    @Autowired
    private lateinit var subCategoryRepository: SubCategoryRepository

    override fun getAll(categoryId: Long, active: Boolean): List<SubCategoryResponseDTO> {
        val result = subCategoryRepository.getAll(categoryId, active)
        val response = mutableListOf<SubCategoryResponseDTO>()

        for (item in result) {
            response.add(getResponseDTO(item))
        }

        return response
    }

    override fun save(request: SubCategoryRequestDTO): SubCategoryResponseDTO {
        var subCategory: SubCategoryEntity
        val timestamp = LocalDateTime.now(Clock.systemUTC())

        if (request.id != null) {
            subCategory = validateSubCategory(request.id)

            subCategory.subTitle = request.subTitle
            subCategory.title = request.title
            subCategory.summary = request.summary
            subCategory.link = request.link
            subCategory.imageUrl = request.imageUrl
            subCategory.active = request.active
            subCategory.updatedBy = request.user
            subCategory.updatedAt = timestamp
        } else {
            subCategory = SubCategoryEntity(
                    categoryId = request.categoryId,
                    subTitle = request.subTitle,
                    title = request.title,
                    summary = request.summary,
                    link = request.link,
                    imageUrl = request.imageUrl,
                    active = request.active,
                    createdAt = timestamp,
                    updatedAt = timestamp,
                    createdBy = request.user,
                    updatedBy = request.user
            )
        }

        subCategory = subCategoryRepository.save(subCategory)
        return getResponseDTO(subCategory)
    }

    override fun disable(subCategoryId: Long) {
        val subCategory = validateSubCategory(subCategoryId)

        if (!subCategory.active)
            throw BadRequestException(ErrorMessage.BAD_REQUEST, "sub category already disabled")

        subCategory.active = false
        subCategory.updatedAt = LocalDateTime.now(Clock.systemUTC())

        subCategoryRepository.save(subCategory)
    }

    private fun validateSubCategory(subCategoryId: Long): SubCategoryEntity {
        val subCategory: SubCategoryEntity
        try {
            subCategory = subCategoryRepository.getOne(subCategoryId)
        } catch (e: EntityNotFoundException) {
            throw BadRequestException(ErrorMessage.BAD_REQUEST, "sub category not found")
        }

        return subCategory
    }

    private fun getResponseDTO(payload: SubCategoryEntity): SubCategoryResponseDTO {
        return SubCategoryResponseDTO(
                id = payload.id!!,
                categoryId = payload.categoryId,
                subTitle = payload.subTitle,
                title = payload.title,
                summary = payload.summary,
                link = payload.link,
                imageUrl = payload.imageUrl,
                active = payload.active
        )
    }
}