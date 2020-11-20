
package com.mycompany.assignment.dao;

import com.mycompany.assignment.table.Product;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class ProductDao {

     private SessionFactory factory;

	public ProductDao(SessionFactory factory) {
		super();
		this.factory = factory;
	}
	
	public boolean saveProduct(Product product ) {
		
		boolean f = false;
		try {
			
			Session session = this.factory.openSession();
			Transaction tx = session.beginTransaction();
			
			session.save(product);
			
			tx.commit();
			session.close();
			f = true;
		
		}catch(Exception e ) {e.printStackTrace();f = false;}
		
			return f ;
	
	}
	
	// get all products 
 	public List<Product> getAllProducts()
	{
		
		Session session = this.factory.openSession();
		Query q = session.createQuery("from Product");
		List<Product> list = q.list();
		return list;
	
		
	}
 	//get all products of given category
	public List<Product> getAllProductsById(int cid)
	{
		
		Session session = this.factory.openSession();
		Query q = session.createQuery("from Product as p where p.category.catId=:id");
		q.setParameter("id", cid );
		List<Product> list = q.list();
		return list; 
	
		
	}

    
}
