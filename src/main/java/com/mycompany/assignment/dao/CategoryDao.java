
package com.mycompany.assignment.dao;

import com.mycompany.assignment.table.Category;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CategoryDao {
    
    private SessionFactory factory;

	public CategoryDao(SessionFactory factory) {
		super();
		this.factory = factory;
	}
	
	public int saveCategory(Category cat) {
		
	 
		
		Session session = this.factory.openSession();
		Transaction tx = session.beginTransaction();
		int catId= (Integer) session.save(cat);
		tx.commit();
		session.close();
		return catId;
	} 

	public List<Category> getCategories(){
		
		Session session = this.factory.openSession();
		Query q = session.createQuery("from Category");
		List<Category> list = q.list();
		return list;
		}
	
	public Category getCategoryById(int catid)
	{
		Category cat= null;
		
		try {
			
			Session session = this.factory.openSession();
			cat = (Category) session.get(Category.class, catid);
			session.close();
		}catch(Exception e) {e.printStackTrace();}
		
		return cat;
		
		
	}
    
}
