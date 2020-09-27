package com.tesla.resource_category.repo

import com.tesla.resource_category.entity.SubSubCategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SubSubCategoryRepository: JpaRepository<SubSubCategoryEntity, Long> {
    @Query("SELECT ssc FROM SubSubCategoryEntity ssc " +
            "WHERE ssc.subCategoryId = :subCategoryId " +
            "AND ssc.active = :isActive ")
    fun getAll(subCategoryId: Long, isActive: Boolean = true): List<SubSubCategoryEntity>
}