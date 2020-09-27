package com.tesla.resource_category.repo

import com.tesla.resource_category.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository: JpaRepository<CategoryEntity, Long> {
    @Query("SELECT c FROM CategoryEntity c " +
            "WHERE c.active = :isActive " +
            "ORDER BY c.name ")
    fun getAll(isActive: Boolean = true): List<CategoryEntity>
}