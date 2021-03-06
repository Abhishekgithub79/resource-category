package com.tesla.resource_category.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SubCategoryRequestDTO (
        val id: Long? = null,

        @JsonProperty("category_id")
        val categoryId: Long,

        @JsonProperty("sub_title")
        var subTitle: String,

        var title: String,

        var summary: String?,

        var link: String?,

        @JsonProperty("image_url")
        val imageUrl: String?,

        @JsonProperty("is_active")
        val active: Boolean = true,

        val user: String = "admin"
)