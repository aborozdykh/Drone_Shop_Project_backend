package com.example.drone_shop_project_backend.service.impl;

import com.example.drone_shop_project_backend.model.Category;
import com.example.drone_shop_project_backend.model.dto.category.CategoryDto;
import com.example.drone_shop_project_backend.model.mapper.CategoryMapper;
import com.example.drone_shop_project_backend.repository.helper.CategoryRepositoryHelper;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepositoryHelper categoryRepositoryHelper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private static final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    private Category category;
    private CategoryDto categoryDto;

    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Drones");

        categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1L);
        categoryDto.setName("Drones");
        categoryDto.setProducts(new ArrayList<>());
    }

    @Test
    public void testPostCategoryWhenCategoryIsPostedThenReturnCategoryDto() {
        when(categoryRepositoryHelper.save(any(Category.class))).thenReturn(category);

        CategoryDto result = categoryService.postCategory(categoryDto);

        assertThat(result).isEqualTo(categoryDto);
        verify(categoryRepositoryHelper, times(1)).save(any(Category.class));
    }

    @Test
    public void testGetCategoryWhenCategoryIsFoundThenReturnCategoryDto() {
        when(categoryRepositoryHelper.findById(anyLong())).thenReturn(category);

        CategoryDto result = categoryService.getCategory(1L);

        assertThat(result).isEqualTo(categoryDto);
        verify(categoryRepositoryHelper, times(1)).findById(anyLong());
    }

    @Test
    public void testPutCategoryWhenCategoryIsUpdatedThenReturnCategoryDto() {
        when(categoryRepositoryHelper.findById(anyLong())).thenReturn(category);
        when(categoryRepositoryHelper.save(any(Category.class))).thenReturn(category);

        CategoryDto result = categoryService.putCategory(categoryDto);

        assertThat(result).isEqualTo(categoryDto);
        verify(categoryRepositoryHelper, times(1)).findById(anyLong());
        verify(categoryRepositoryHelper, times(1)).save(any(Category.class));
    }

    @Test
    public void testDeleteCategoryWhenCategoryIsDeletedThenVerifyDeleteByIdIsCalled() {
        doNothing().when(categoryRepositoryHelper).deleteById(anyLong());

        categoryService.deleteCategory(1L);

        verify(categoryRepositoryHelper, times(1)).deleteById(anyLong());
    }
}
