
<%@page import="com.mycompany.assignment.table.Product"%>
<%@page import="com.mycompany.assignment.dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.assignment.dao.CategoryDao"%>
<%@page import="com.mycompany.assignment.table.Category"%>
<%@page import="com.mycompany.assignment.helper.FactoryProvider"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="components/common_css_js.jsp"%>
       
    </head>
    <body>
        <div class="mt-4 custom-bg">
            <span class=""><center><h1> First Assignment </h1></center></span>
</div>        
        <div class="container">
            <div class="row mt-5">        

<!--  first column -->

<div class="col-md-6">
<div class="card text-center custom-bg" data-toggle="modal"data-target="#add-product-modal">
<div class="card-body">
<h4>Add Product</h4>
</div>
</div>
</div>

<!-- Second column -->

<div class="col-md-6">
<div class="card text-center custome-bg" data-toggle="modal"data-target="#add-category-modal">
<div class="card-body">
<h4>Add Category</h4>
</div>
</div>
</div>
</div>
<!-- close first row -->

            
            </div>
<!-- Add-product-modal -->


<div class="modal fade" id="add-product-modal" tabindex="-1" role="dialog" aria-labelledby="add-product-modal" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header custom-bg text-white">
        <h5 class="modal-title" id="exampleModalLabel">Fill product details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        
        <form action="OperationServlet"method="post"enctype="multipart/form-data">
        <input type="hidden" name="operation"value="addProduct">
        <div class="form-group">
        <input type="text" class="form-control" name="pname" placeholder="Enter product title" required />
         </div>
                 
         <div class="form-group">
        <input type="number" class="form-control" name="pprice" placeholder="Enter product price" required />
         </div>
        
        <div class="form-group">
        <input type="number" class="form-control" name="pdiscount" placeholder="Enter product discount" required />
         </div>
        
      
       <!-- Add cateogry to add product option dynamically -->
       
       <%
       CategoryDao cd = new CategoryDao(FactoryProvider.getFactory());
			List<Category> list = cd.getCategories();
       %>
       
       <div class="form-group">
       <select class="form-control" name="catId">
      <% 
       for(Category c:list)
       {
     %>
       <option value="<%=c.getCatId()%>"><%=c.getCatTitle()%></option>
    <% 
       }
      %>
       
       </select>
         </div>
         
        <!-- Product file -->
        
        <div class="form-group">
        
   <label for="file">Choose file to upload</label>
   <br>
   <input type="file" id="file" name="pPic" >

        </div>
             
        <div class="container text-center">
        <button class="btn btn-outline-success">Add Product</button>
        <button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
        </div>
        
        </form>
        
      </div>
    </div>
  </div>
</div>
      
      
      
      <!-- category model -->

<!-- Modal -->
<div class="modal fade" id="add-category-modal" tabindex="-1" role="dialog" aria-labelledby="add-category-modal" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header custom-bg text-white">
        <h5 class="modal-title" id="exampleModalLabel">Fill Category</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        
        <form action="OperationServlet">
       
        <input type="hidden"name="operation"value="addCategory">
       
        <div class="form-group">
        <input type="text" class="form-control" name="catTitle" placeholder="Enter category title" required />
         </div>
        
        <div class="form-group">
        <textarea style="height: 200px"  class="form-control" name="catDescription" placeholder="Write description" required>
        </textarea>
        </div>
        
        <div class="container text-center">
        <button class="btn btn-outline-success">Add Category</button>
        <button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
        </div>
        
        </form>
        
      </div>
     </div>
  </div>
</div>


      
           <div class="container-fluid">
<div class="row mt-3">
<%
String cat = request.getParameter("category2");


ProductDao d = new ProductDao(FactoryProvider.getFactory());
List<Product> list3=null;
//out.println(cat);
 

 if(cat==null ||cat.equals("all"))
 {
	 list3  = d.getAllProducts();
//	out.println("Hii...");	 
 }
 else
 {
	int cid = Integer.parseInt(cat);
	list3=d.getAllProductsById(cid);
 }
  
  
  CategoryDao dao = new CategoryDao(FactoryProvider.getFactory());
  List<Category> clist = dao.getCategories();
   
%>


<!-- Category section -->

<div class="col-md-2 col-sm-3">
<div class="list-group mt-4 m-2">
  
    <a href="index.jsp?category2=all" class="list-group-item list-group-item-action active">
    All Categories
  </a>
  
 

<% for(Category category:clist)
{
%>

<a href="index.jsp?category2=<%=category.getCatId() %>" class="list-group-item list-group-item-action"><%= category.getCatTitle() %></a>
<% 
}
%>
 </div>
</div>


<!-- Product section -->

<div class="col-lg-10 col-md-10 col-sm-8 col-xs-8">

<div class="row mt-4">

<!-- col 12  -->

<div class="col-md-12">

<div class="card-columns">

<!--  traversing products -->


<%
for(Product p : list3){
%>
<!-- product list -->
<div class="card product-card">

<div class="container text-center">
<img src="images/<%= p.getpPhoto()%>" style="max-height: 200px; max-width: 100%; width: auto;"class="card-img-top m-2"alt="...">

</div>



<div class="card-body">
<h5 class="card-title"><%=p.getpName() %> </h5>



</div>

<!-- card-footer -->

<div class="card-footer text-center">
    <button class="btn custome-bg text-white btn-sm"onclick="add_to_cart(<%= p.getpId()%>, '<%= p.getpName()%>' , <%= p.getProductPriceAfterApplyingDiscount()%>)" > Add to cart</button>
<button class="btn btn-outline-primary btn-sm my-1">&#8377;<%=p.getProductPriceAfterApplyingDiscount()%> /- <span class="text-secondary discount-label" >&#8377;<%= p.getpPrice()%>, <%=p.getpDiscount()%>% off</span></button>
</div>
</div>

<%
}

if(list3.size()==0){out.println("No item is in this cateogory");}
%>
</div>

</div>

</div>

</div>
 
</div>
</div>
        
    </body>
</html>



