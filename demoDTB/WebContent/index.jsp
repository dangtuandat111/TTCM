<%@page import="java.sql.ResultSet"%>
<%@page import="model.MyConnectDB"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Demo Connect DTB</title>
	<style type="text/css">
		.row {  
			margin-top:40px;    
			padding: 0 10px;
		}
		.clickable {    
			cursor: pointer;
		}
		.panel-heading div {    
			margin-top: -18px;  
			font-size: 15px;
		}
		.panel-heading div span {   
			margin-left:5px;
		}
		.panel-body {   
			display: none;
		}
	</style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	

</head>
<body>
	<div class="container"> 
		<h2 align="center">Product</h2> 
		<div class="row"> 
			<div class="col-md-12"> 
						<a href="ThemSanPham.jsp"><button class="btn btn-success">Thêm sản phẩm</button></a>
				<div class="panel panel-primary"> 
					<div class="panel-heading"> 
						<h3 class="panel-title">Danh sách sản phẩm</h3> 
						
					</div> 
					<div class="panel-body"> <input class="form-control" id="dev-table-filter" data-action="filter" data-filters="#dev-table" placeholder="Từ khóa" type="text"> 
					</div> 
					
					<%
					MyConnectDB con = new MyConnectDB();
					ResultSet rs = con.chonDuLieuTuDTB("select * from PRODUCT");
					
				
					%>
					<table class="table table-hover" id="dev-table"> 
						<thead> 
							<tr> 
								<th>ID</th> 
								<th>Tên sp</th> 
								<th>HSD</th> 
								<th>Phân loại</th> 
								<th>Chi tiết</th>
								<th>Đơn vị</th>
							</tr> 
						</thead> 
						<tbody>
						<%
						while(rs.next()){
						%>
						
						<tr> 
							<td><%=rs.getString(1) %></td> 
							<td><%=rs.getString(2) %></td> 
							<td><%=rs.getString(3) %></td> 
							<td><%=rs.getString(4) %></td> 
							<td><a href="XoaSP?stt=<%=rs.getString(1)%>"><button class="btn btn-warning">Xóa</button></a></td>
							<td><a href="SuaSP?stt=<%=rs.getString(1)%>"><button class="btn btn-primary">Sửa</button></a></td>
						</tr> 
						<%
						}
						%>
						
					</tbody></table> 
				</div> 
			</div> 
		</div>
		
	
		
	</div>
	
	</body>
	</html>