
package com.mycompany.assignment.table;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Category {

        @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 10, name = "category_Id")
	private int catId;
	@Column(length = 100, name = "category_Title")
	private String catTitle;
	@Column(length = 2900, name = "category_Description")
	private String catDescription;
	
	//private Product product;  When we have only 1 product but we have in one category many products
	@OneToMany(mappedBy = "category")     
	 // One Category has many products
	private List<Product> products = new ArrayList<Product>();  //we can also use hasSet when we have lots of products.
	
	
	public Category(int catId, String catTitle, String catDescription) {
		//super();
		this.catId = catId;
		this.catTitle = catTitle;
		this.catDescription = catDescription;
	}
	public Category(String catTitle, String catDescription, List<Product> products) {
		//super();
		this.catTitle = catTitle;
		this.catDescription = catDescription;
		this.products=products;
	}
	public Category() {
		//super();
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public String getCatTitle() {
		return catTitle;
	}
	public void setCatTitle(String catTitle) {
		this.catTitle = catTitle;
	}
	public String getCatDescription() {
		return catDescription;
	}
	public void setCatDescription(String catDescription) {
		this.catDescription = catDescription;
	}
	
	

	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	

	@Override
	public String toString() {
		return "Category [catId=" + catId + ", catTitle=" + catTitle + ", catDescription=" + catDescription + "]";
	}

    
}
