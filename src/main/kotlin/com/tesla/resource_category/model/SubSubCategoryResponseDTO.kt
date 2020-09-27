package com.tesla.resource_category.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SubSubCategoryResponseDTO (
        val id: Long,

        @JsonProperty("sub_category_id")
        val subCategoryId: Long,

        var title: String,

        var link: String?,

        @JsonProperty("image_url")
        val imageUrl: String?,

        @JsonProperty("is_active")
        val active: Boolean = true
)