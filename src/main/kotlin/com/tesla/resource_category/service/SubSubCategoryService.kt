package com.tesla.resource_category.service

import com.tesla.resource_category.entity.SubSubCategoryEntity
import com.tesla.resource_category.exception.ErrorMessage
import com.tesla.resource_category.exception.custom_exceptions.BadRequestException
import com.tesla.resource_category.model.SubSubCategoryRequestDTO
import com.tesla.resource_category.model.SubSubCategoryResponseDTO
import com.tesla.resource_category.repo.SubSubCategoryRepository
import com.tesla.resource_category.service.iservice.ISubSubCategoryService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException

@Service
class SubSubCategoryService: ISubSubCategoryService {
    companion object {
        private val log = LoggerFactory.getLogger(SubSubCategoryService::class.java)
    }

    @Autowired
    private lateinit var subSubCategoryRepository: SubSubCategoryRepository

    override fun getAll(subCategoryId: Long, active: Boolean): List<SubSubCategoryResponseDTO> {
        val result = subSubCategoryRepository.getAll(subCategoryId, active)
        val response = mutableListOf<SubSubCategoryResponseDTO>()

        for (item in result) {
            response.add(getResponseDTO(item))
        }

        return response
    }

    override fun save(request: SubSubCategoryRequestDTO): SubSubCategoryResponseDTO {
        var subSubCategory: SubSubCategoryEntity
        val timestamp = LocalDateTime.now(Clock.systemUTC())

        if (request.id != null) {
            subSubCategory = validateSubSubCategory(request.id)

            subSubCategory.title = request.title
            subSubCategory.link = request.link
            subSubCategory.imageUrl = request.imageUrl
            subSubCategory.active = request.active
            subSubCategory.updatedBy = request.user
            subSubCategory.updatedAt = timestamp
        } else {
            subSubCategory = SubSubCategoryEntity(
                    subCategoryId = request.subCategoryId,
                    title = request.title,
                    link = request.link,
                    imageUrl = request.imageUrl,
                    active = request.active,
                    createdAt = timestamp,
                    updatedAt = timestamp,
                    createdBy = request.user,
                    updatedBy = request.user
            )
        }

        subSubCategory = subSubCategoryRepository.save(subSubCategory)
        return getResponseDTO(subSubCategory)
    }

    override fun disable(subCategoryId: Long) {
        val subSubCategory = validateSubSubCategory(subCategoryId)

        if (!subSubCategory.active)
            throw BadRequestException(ErrorMessage.BAD_REQUEST, "sub sub category already disabled")

        subSubCategory.active = false
        subSubCategory.updatedAt = LocalDateTime.now(Clock.systemUTC())

        subSubCategoryRepository.save(subSubCategory)
    }

    private fun validateSubSubCategory(subSubCategoryId: Long): SubSubCategoryEntity {
        val subSubCategory: SubSubCategoryEntity
        try {
            subSubCategory = subSubCategoryRepository.getOne(subSubCategoryId)
        } catch (e: EntityNotFoundException) {
            throw BadRequestException(ErrorMessage.BAD_REQUEST, "sub sub category not found")
        }

        return subSubCategory
    }

    private fun getResponseDTO(payload: SubSubCategoryEntity): SubSubCategoryResponseDTO {
        return SubSubCategoryResponseDTO(
                id = payload.id!!,
                subCategoryId = payload.subCategoryId,
                title = payload.title,
                link = payload.link,
                imageUrl = payload.imageUrl,
                active = payload.active
        )
    }
}