package com.mycompany.assignment.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product {
 
    	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 10,name = "product_Id")
	private int pId;
	@Column(length = 100,name = "product_Name")
	private String pName;
	private String pPhoto;
	@Column(length = 10, name = "product_Price")
	private int pPrice;
	@Column(length = 10,name = "product_Discount")
	private int pDiscount;
        
        @ManyToOne
	private Category category;
	

    public Product(String pName, String pPhoto, int pPrice, int pDiscount) {
        this.pName = pName;
        this.pPhoto = pPhoto;
        this.pPrice = pPrice;
        this.pDiscount = pDiscount;
    }

    public Product(int pId, String pName, String pPhoto, int pPrice, int pDiscount, Category category) {
        this.pId = pId;
        this.pName = pName;
        this.pPhoto = pPhoto;
        this.pPrice = pPrice;
        this.pDiscount = pDiscount;
        this.category = category;
    }

    public Product() {
       // super();
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setpPhoto(String pPhoto) {
        this.pPhoto = pPhoto;
    }

    public void setpPrice(int pPrice) {
        this.pPrice = pPrice;
    }

    public void setpDiscount(int pDiscount) {
        this.pDiscount = pDiscount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getpId() {
        return pId;
    }

    public String getpName() {
        return pName;
    }

    public String getpPhoto() {
        return pPhoto;
    }

    public int getpPrice() {
        return pPrice;
    }

    public int getpDiscount() {
        return pDiscount;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" + "pId=" + pId + ", pName=" + pName + ", pPhoto=" + pPhoto + ", pPrice=" + pPrice + ", pDiscount=" + pDiscount + ", category=" + category + '}';
        
    }
    
    

    public int getProductPriceAfterApplyingDiscount()
	{
		int d = (int) ((this.getpDiscount()/100.0)*this.getpPrice());
		return this.getpPrice()-d; 
	}
        
        
       
    
}
