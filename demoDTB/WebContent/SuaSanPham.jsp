<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2>Sửa thông tin sản phẩm</h2>
  <form class="form-horizontal" action="SuaSP" method="post">
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">ID:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="email" name="id" placeholder="Nhập vào id" value="<%
        	ServletContext context = getServletContext();
       	 out.print(context.getAttribute("id"));
        %>">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Tên sp:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="pwd" name="ten" placeholder="Nhập vào tên" value="<%
        	ServletContext context2 = getServletContext();
       	 out.print(context2.getAttribute("ten"));
        %>">
      </div>
    </div>
  <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Phân loại:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="pwd" name="pl" placeholder="Nhập vào tài pl" value="<%
        	ServletContext context3 = getServletContext();
       	 out.print(context3.getAttribute("pl"));
        %>">
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default">Sửa</button>
      </div>
    </div>
  </form>
</div>

</body>
</html>