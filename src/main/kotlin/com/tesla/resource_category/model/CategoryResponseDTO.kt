package com.tesla.resource_category.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CategoryResponseDTO (
        val id: Long,

        val name: String,

        @JsonProperty("image_url")
        val imageUrl: String?,

        @JsonProperty("is_active")
        val active: Boolean = true
)