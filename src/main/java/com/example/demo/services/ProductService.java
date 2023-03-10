package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Favorite;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.model.FavoriteProduct;
import com.example.demo.model.ProductModel;

public interface ProductService {
	public abstract Product addProduct(ProductModel product);
	public abstract List<ProductModel> listAllProducts();
	public abstract Product findProductById(int id);
	public abstract ProductModel findProductByIdModel(int id);
	public abstract boolean removeProduct(int id);
	public abstract boolean removeProductsOfCategory(int categoryId);
	public abstract Product transform(ProductModel productModel);
	public abstract ProductModel transform(Product product);
	public abstract Product updateProduct(ProductModel product);
	public abstract List<ProductModel> listProductByCategory(int idCategory);
	public abstract List<ProductModel> listProducts();
	public abstract List<ProductModel> listProductsFavorites();
	public abstract  Favorite addProductFavorite(ProductModel product);
}
