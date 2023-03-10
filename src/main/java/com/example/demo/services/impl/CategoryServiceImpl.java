package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.model.CategoryModel;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.services.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	@Qualifier("categoryRepository")
	private CategoryRepository categoryRepository;
	
	@Autowired
	@Qualifier("productRepository")
	private ProductRepository productRepository;

	@Override
	public Category addCategory(CategoryModel categoryModel) {
		// TODO Auto-generated method stub
		return categoryRepository.save(transform(categoryModel));
	}
	
	@Override
	public Category updateCategory(CategoryModel categoryModel) {
		// TODO Auto-generated method stub
		return categoryRepository.save(transform(categoryModel));
	}


	@Override
	public List<CategoryModel> listAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category findCategoryById(int id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id);
	}

	@Override
	public CategoryModel findCategoryModelById(int id) {
		// TODO Auto-generated method stub
		return transform(categoryRepository.findById(id));
	}

	@Override
	public boolean removeCategory(int id) {
		categoryRepository.deleteById(id);
		return false;
	}
	
	@Override
	public boolean removeProductsAndCategory(int categoryId) {
		List<Product> products = productRepository.findByCategoryId(categoryId);
		for(Product p : products) {
			productRepository.delete(p);
		}
		categoryRepository.deleteById(categoryId);
		return false;
	}

	@Override
	public Category transform(CategoryModel categoryModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(categoryModel, Category.class);
	}

	@Override
	public CategoryModel transform(Category category) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(category, CategoryModel.class);
	}

	@Override
	public List<CategoryModel> findCategories() {
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		for(Category c : categoryRepository.findAll()) {
			categories.add(transform(c));
		}
		return categories;
	}
}
