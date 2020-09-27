package com.tesla.resource_category.entity

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "sub_category")
data class SubCategoryEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(name = "category_id")
        var categoryId: Long,

        @Column(name = "sub_title")
        var subTitle: String,

        var title: String,

        var summary: String?,

        var link: String?,

        @Column(name = "image_url")
        var imageUrl: String?,

        @Column(name = "is_active")
        var active: Boolean,

        @Column(name = "created_at")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        var createdAt: LocalDateTime? = null,

        @Column(name = "updated_at")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        var updatedAt: LocalDateTime? = null,

        @Column(name = "created_by")
        var createdBy: String,

        @Column(name = "updated_by")
        var updatedBy: String
)