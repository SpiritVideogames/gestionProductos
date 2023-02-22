package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.model.ProductModel;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.services.ProductService;

import jakarta.transaction.Transactional;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	@Qualifier("productRepository")
	private ProductRepository productRepository;
	
	@Autowired
	@Qualifier("categoryRepository")
	private CategoryRepository categoryRepository;

	@Override
	public Product addProduct(ProductModel product) {
		// TODO Auto-generated method stub
		return productRepository.save( transform(product));
	}
	
	@Override
	public Product updateProduct(ProductModel product) {
		// TODO Auto-generated method stub
		return productRepository.save( transform(product));
	}


	@Override
	public List<ProductModel> listAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findProductById(int id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id);
	}

	@Override
	public ProductModel findProductByIdModel(int id) {
		// TODO Auto-generated method stub
		return transform(productRepository.findById(id));
	}
	
	@Override
	public List<ProductModel> listProductByCategory(int categoryId){
		
		List<ProductModel> products = new ArrayList<ProductModel>();
		for (Product product : productRepository.findByCategoryId(categoryId))
			products.add(transform(product));
		return products;
		
	}
	
	@Override
	public List<ProductModel> listProductByFavorite(boolean favorite){
		
		List<ProductModel> products = new ArrayList<ProductModel>();
		for (Product product : productRepository.findByFavorite(favorite))
			products.add(transform(product));
		return products;
		
	}
	@Override
	public boolean removeProduct(int id) {
		productRepository.deleteById(id);
		return false;
	}

	@Transactional
	@Override
	public boolean removeProductsOfCategory(int categoryId) {
		Category category = categoryRepository.findById(categoryId);
		productRepository.deleteAllByCategory(category);
		return false;
	}

	@Override
	public Product transform(ProductModel productModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(productModel, Product.class);
	}

	@Override
	public ProductModel transform(Product category) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(category, ProductModel.class);
	}

	@Override
	public List<ProductModel> listProducts() {
		List<ProductModel> productsModel = new ArrayList<ProductModel>();
		for(Product p : productRepository.findAll()) {
			productsModel.add(transform(p));
		}
		return productsModel;
	}

}
