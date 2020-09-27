package com.tesla.resource_category.repo

import com.tesla.resource_category.entity.SubCategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SubCategoryRepository: JpaRepository<SubCategoryEntity, Long> {
    @Query("SELECT sc FROM SubCategoryEntity sc " +
            "WHERE sc.categoryId = :categoryId " +
                "AND sc.active = :isActive ")
    fun getAll(categoryId: Long, isActive: Boolean = true): List<SubCategoryEntity>
}