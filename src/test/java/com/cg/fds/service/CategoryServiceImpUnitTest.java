package com.cg.fds.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.fds.entities.Category;
import com.cg.fds.exception.CategoryNotFoundException;
import com.cg.fds.exception.InvalidCategoryException;

import com.cg.fds.repository.ICategoryRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImpUnitTest {
	
	@Mock
	ICategoryRepository categoryRepository;
	@Spy
	@InjectMocks
	CategoryServiceImp categoryService;
	
	
	
	/**
	 * scenario : Creating and Adding the category to the database
	 *     expect: cat  , output:cat 
	 */
	
	@Test
	public void addCategoryTest()
	{
		String id="1";
		Category cat=Mockito.mock(Category.class);
		Category catSaved=Mockito.mock(Category.class);
		Mockito.doNothing().when(categoryService).validateCategory(cat);
		Mockito.doReturn(id).when(categoryService).generateId();
		Mockito.when(cat.getCatId()).thenReturn(id);
		Mockito.when(categoryRepository.save(cat)).thenReturn(catSaved);
		Category result=categoryService.addCategory(cat);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(catSaved,result);
		Mockito.verify(cat).setCatId(id);
		
	}
	
	@Test
	public void updateCategoryTest_1()
	{
		String id="1";
		Category cat=Mockito.mock(Category.class);
		Category catSaved=Mockito.mock(Category.class);
		Mockito.doNothing().when(categoryService).validateCategory(cat);
		Mockito.when(cat.getCatId()).thenReturn(id);
		Mockito.when(categoryRepository.existsById(id)).thenReturn(true);
		Mockito.when(categoryRepository.save(cat)).thenReturn(catSaved);
		Category result=categoryService.updateCategory(cat);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(catSaved,result);
		
	}
	
	@Test
	public void updateCategoryTest_2()
	{
		String id="1";
		Category cat=Mockito.mock(Category.class);
		Mockito.doNothing().when(categoryService).validateCategory(cat);
		Mockito.when(cat.getCatId()).thenReturn(id);
		Mockito.when(categoryRepository.existsById(id)).thenReturn(false);
		Executable executable = () -> categoryService.updateCategory(cat);
		Assertions.assertThrows(CategoryNotFoundException.class, executable);
		
	}
	
	/**
	 * scenario : When category is null, Exception will be thrown
	 */
	
	@Test void ValidCategory()
	{
		Category cat=null;
		Executable executable = () -> categoryService.validateCategory(cat);
		Assertions.assertThrows(InvalidCategoryException.class, executable);
		
	}

}
