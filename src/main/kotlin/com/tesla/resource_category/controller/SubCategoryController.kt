package com.tesla.resource_category.controller

import com.tesla.resource_category.model.SubCategoryRequestDTO
import com.tesla.resource_category.service.SubCategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/sub_category", produces = [MediaType.APPLICATION_JSON_VALUE])
class SubCategoryController {
    @Autowired
    private lateinit var subCategoryService: SubCategoryService

    @GetMapping("/all")
    fun getAll(@RequestParam(value = "category_id") categoryId: Long,
               @RequestParam(value = "active", required = false, defaultValue = "true") active: Boolean) =
            subCategoryService.getAll(categoryId, active)

    @PostMapping("/save", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun save(@RequestBody request: SubCategoryRequestDTO) = subCategoryService.save(request)

    @PutMapping("/disable")
    fun disable(@RequestParam(value = "sub_category_id") subCategoryId: Long) = subCategoryService.disable(subCategoryId)
}