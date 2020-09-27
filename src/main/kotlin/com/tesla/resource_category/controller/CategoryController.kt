package com.tesla.resource_category.controller

import com.tesla.resource_category.model.CategoryRequestDTO
import com.tesla.resource_category.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/category", produces = [MediaType.APPLICATION_JSON_VALUE])
class CategoryController {
    @Autowired
    private lateinit var categoryService: CategoryService

    @GetMapping("/all")
    fun getAll(@RequestParam(value = "active", required = false, defaultValue = "true") active: Boolean) = categoryService.getAll(active)

    @PostMapping("/save", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun save(@RequestBody request: CategoryRequestDTO) = categoryService.save(request)

    @PutMapping("/disable")
    fun disable(@RequestParam(value = "category_id") categoryId: Long) = categoryService.disable(categoryId)

    // TODO:
    // 1. get all active categories
    // 2. get all sub categories based on category id
    // 2.b get all sub-sub categories information based on sub category id
    // 3. [OPTIONAL] get all inactive categories, sub categories and sub-sub categories

    // 4. Add/update new category
    // 5. Add/update sub category under category id
    // 6. Add/update sub-sub category under sub-category id

    // 7. delete category
    // 8. delete sub category
    // 9. delete sub-sub category
}