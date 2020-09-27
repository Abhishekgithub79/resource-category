package com.tesla.resource_category.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CategoryRequestDTO (
        val id: Long? = null,

        val name: String,

        @JsonProperty("image_url")
        val imageUrl: String? = null,

        @JsonProperty("is_active")
        val active: Boolean = true,

        val user: String = "admin"
)