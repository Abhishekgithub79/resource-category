package com.tesla.resource_category.controller

import com.tesla.resource_category.model.SubSubCategoryRequestDTO
import com.tesla.resource_category.service.SubSubCategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/sub_sub_category", produces = [MediaType.APPLICATION_JSON_VALUE])
class SubSubCategoryController {
    @Autowired
    private lateinit var subSubCategoryService: SubSubCategoryService

    @GetMapping("/all")
    fun getAll(@RequestParam(value = "sub_category_id") subCategoryId: Long,
               @RequestParam(value = "active", required = false, defaultValue = "true") active: Boolean) =
            subSubCategoryService.getAll(subCategoryId, active)

    @PostMapping("/save", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun save(@RequestBody request: SubSubCategoryRequestDTO) = subSubCategoryService.save(request)

    @PutMapping("/disable")
    fun disable(@RequestParam(value = "sub_sub_category_id") subSubCategoryId: Long) =
            subSubCategoryService.disable(subSubCategoryId)
}