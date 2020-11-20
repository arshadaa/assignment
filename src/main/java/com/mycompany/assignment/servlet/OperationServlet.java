
package com.mycompany.assignment.servlet;

import com.mycompany.assignment.dao.CategoryDao;
import com.mycompany.assignment.dao.ProductDao;
import com.mycompany.assignment.helper.FactoryProvider;
import com.mycompany.assignment.table.Category;
import com.mycompany.assignment.table.Product;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class OperationServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
          response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
       
                    String op = request.getParameter("operation");
    if(op.trim().equals("addCategory"))
    {
    	//Fetching category data
    String title = 	request.getParameter("catTitle");
    String desc = request.getParameter("catDescription");
  //  System.out.println(title);
    Category category = new Category();
    category.setCatTitle(title);
    category.setCatDescription(desc);
   //category database save;
    
    CategoryDao categoryDao = new CategoryDao(FactoryProvider.getFactory());  
    int catId= categoryDao.saveCategory(category);
   //for show the mesage to the admin panel
   HttpSession httpsession = request.getSession();
   httpsession.setAttribute("message", "Category added successfully : "+catId);
   response.sendRedirect("index.jsp");
   //out.println("saved"); 
    
    	
    }else if(op.trim().equals("addProduct"))
    {
   
    String pName = request.getParameter("pname");
    int pPrice = Integer.parseInt(request.getParameter("pprice"));
    int pDiscount = Integer.parseInt(request.getParameter("pdiscount"));
    int catId = Integer.parseInt(request.getParameter("catId"));
   Part part = request.getPart("pPic");			//For fetch the images or files.
  //String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
   
    
        Product p = new Product();
    p.setpName(pName);
    p.setpPrice(pPrice);
    p.setpDiscount(pDiscount);
    p.setpPhoto(part.getSubmittedFileName());
    	// get category by id
    
        CategoryDao categoryDao = new CategoryDao(FactoryProvider.getFactory());
        Category category = categoryDao.getCategoryById(catId);
        p.setCategory(category);
    
    // product save  
        ProductDao productDao = new ProductDao(FactoryProvider.getFactory());
          productDao.saveProduct(p);
         // out.println("Product save succesfully");
          
     
     
    // pic upload
    // find out the path to upload photo
    
    
    String path = request.getRealPath("images")+File.separator+part.getSubmittedFileName();
    System.out.println(path);
    
   try {
    // upload code
    
    FileOutputStream fos = new FileOutputStream(path);
    InputStream is = part.getInputStream();
    
    //reading data
    
    byte []data = new byte[is.available()];
    
    is.read(data);
    
    // writing data
    
    fos.write(data);
    
    fos.close();
    }
    catch (Exception e) {
		// TODO: handle exception
    	e.printStackTrace();
	}
     String file = Paths.get(part.getSubmittedFileName()).getFileName().toString();
     System.out.println(file);
    
    HttpSession httpsession = request.getSession();
     httpsession.setAttribute("message", "Product added successfuly... ");
     response.sendRedirect("index.jsp");
     
   // out.println("save product");
    }
    }

        

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
