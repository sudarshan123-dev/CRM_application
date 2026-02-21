<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.connection.Dbconn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<!-- Mobile Specific Meta -->
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!-- Favicon-->
		<link rel="shortcut icon" href="img/fav.png">
		<!-- Author Meta -->
		<meta name="author" content="colorlib">
		<!-- Meta Description -->
		<meta name="description" content="">
		<!-- Meta Keyword -->
		<meta name="keywords" content="">
		<!-- meta character set -->
		<meta charset="UTF-8">
		<!-- Site Title -->
		<title>Product Review</title>

		<link href="https://fonts.googleapis.com/css?family=Poppins:100,200,400,300,500,600,700" rel="stylesheet"> 
			<!--
			CSS
			============================================= -->
			<link rel="stylesheet" href="css/linearicons.css">
			<link rel="stylesheet" href="css/font-awesome.min.css">
			<link rel="stylesheet" href="css/bootstrap.css">
			<link rel="stylesheet" href="css/magnific-popup.css">
			<link rel="stylesheet" href="css/nice-select.css">							
			<link rel="stylesheet" href="css/animate.min.css">
			<link rel="stylesheet" href="css/owl.carousel.css">
			<link rel="stylesheet" href="css/main.css">
			</script>
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<style>
@import
	url(http://fonts.googleapis.com/css?family=Roboto:500,100,300,700,400);

* {
	margin: 0;
	padding: 0;
	font-family: roboto;
}

.cont {
	width: 93%;
	max-width: 350px;
	text-align: center;
	margin: 4% auto;
	padding: 30px 0;
	background: #111;
	color: #EEE;
	border-radius: 5px;
	border: thin solid #444;
	overflow: hidden;
}

hr {
	margin: 20px;
	border: none;
	border-bottom: thin solid rgba(255, 255, 255, .1);
}

div.title {
	font-size: 2em;
}

h1 span {
	font-weight: 300;
	color: #Fd4;
}

div.stars {
	width: 270px;
	display: inline-block;
}

input.star {
	display: none;
}

label.star {
	float: right;
	padding: 10px;
	font-size: 36px;
	color: #444;
	transition: all .2s;
}

input.star:checked ~ label.star:before {
	content: '\f005';
	color: #FD4;
	transition: all .25s;
}

input.star-5:checked ~ label.star:before {
	color: #FE7;
	text-shadow: 0 0 20px #952;
}

input.star-1:checked ~ label.star:before {
	color: #F62;
}

label.star:hover {
	transform: rotate(-15deg) scale(1.3);
}

label.star:before {
	content: '\f006';
	font-family: FontAwesome;
}
</style>
		</head>
<body>	
			    <header id="header" id="home">
		  		
			    <div class="container main-menu">
			    	<div class="row align-items-center justify-content-between d-flex">
				      <div id="logo">
				        <jsp:include page="titlepage.jsp"></jsp:include>
				      </div>
				       <nav id="nav-menu-container">
				        <ul class="nav-menu">
				         <jsp:include page="Umenu.jsp"></jsp:include>
				        </ul>
				      </nav><!-- #nav-menu-container -->	 		
			    	</div>
			    </div>
			  </header><!-- #header -->
			  	
			<!-- Start testomial Area -->
			<section class="testomial-area section-gap" id="testimonail">
				<div class="container">
					<div class="row d-flex justify-content-center">
						<div class="menu-content pb-60 col-lg-7">
							<div class="title text-center">
								<h1 class="mb-10 text-white">Tickets Info</h1>
								<br>
							</div>
						<form role="form" action="rating_info" method="post">
			<div class="form-group">
			<table class="table table-responsive table-bordered" >
	      
		<%
	
	String name="";
	String qty="",id="",extra="",manufacture_date="",exp_date="";
	Dbconn db=new Dbconn();
	String ids=request.getParameter("id");
	Connection con=db.conn();
	Statement st=con.createStatement();
	String Shop_Name="";
	String Shop_Nameemail="";
	ResultSet rs=st.executeQuery("select * from help_product_info where id='"+ids+"'");
	if(rs.next())
	{
		id=rs.getString(2);
		name=rs.getString("name");
		Shop_Name=rs.getString("Shop_Name");
		Shop_Nameemail=rs.getString("shop_user_email_id");
		
		 
%>
<tr>
			<td class="col-md-2"><label for="txt_Pname" style="color:White;font-size:18px;">Transaction ID:</label></td>
			<td class="col-md-6"><input type="text"  class="form-control" name="txt_id" required id="txt_id" value="<%=ids %>" readonly="readonly"></td>
			</tr>
			<tr>
			<td class="col-md-2"><label for="txt_Pname" style="color:White;font-size:18px;">Shop Name</label></td>
			<td class="col-md-6"><input type="text"  class="form-control" name="txt_shopname" required id="txt_id" value="<%=Shop_Name %>" readonly="readonly"></td>
			</tr>
			<tr>
			<td class="col-md-2"><label for="txt_Pname" style="color:White;font-size:18px;">Shop EmailID</label></td>
			<td class="col-md-6"><input type="text"  class="form-control" name="txt_emailid" required id="txt_id" value="<%=Shop_Nameemail %>" readonly="readonly"></td>
			</tr>
		    <tr>
			<td class="col-md-2"><label for="txt_Pname" style="color:White;font-size:18px;">Product Name:</label></td>
			<td class="col-md-6"><input type="text" class="form-control" readonly="readonly" name="txt_Pname" required id="txt_Pname" value="<%=name %>" placeholder="Enter Product Name"></td>
			</tr>
			
			
			<tr>
			<td class="col-md-2"><label for="txt_Pdescription" style="color:White;font-size:18px;">Product Feedback</label></td>
			<td class="col-md-6"><textarea class="form-control" name="txt_Pdescription"  required id="txt_Pdescription" placeholder="Enter Product Feedback"></textarea></td>
			</tr>
			<tr>
			<td class="col-md-2"><label for="txt_Pdescription" style="color:White;font-size:18px;">Rating Star</label></td>
			<td align="right"><input class="star star-1" id="star-1"
										type="radio" value="1" name="star" /> <label
										class="star star-1" for="star-1"></label> <input
										class="star star-2" id="star-2" type="radio" value="2"
										name="star" /> <label class="star star-2" for="star-2"></label>
										<input class="star star-3" id="star-3" type="radio" value="3"
										name="star" /> <label class="star star-3" for="star-3"></label>
										<input class="star star-4" id="star-4" type="radio" value="4"
										name="star" /> <label class="star star-4" for="star-4"></label>
										<input class="star star-5" id="star-5" type="radio" value="5"
										name="star" /> <label class="star star-5" for="star-5"></label>
									</td>
			</tr>
			
			<tr>
			<td class="col-md-6" colspan="2" align="center" ><input type="submit" class="btn btn-primary" name="btn_Submit" id="btn_Submit" Value="Save"></td>
			</tr>
			<%} %>
			</table>
			</div>
			</form>

						</div>
					</div>						
					</div>	
			</section>
			<!-- End testomial Area -->				
			
	

			<!-- start footer Area -->		
		<footer class="footer-area" style="height:20px;">
			<p class="col-lg-8 col-sm-12 footer-text m-0">Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a></p>

			</footer>
			<!-- End footer Area -->	

			<script src="js/vendor/jquery-2.2.4.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
			<script src="js/vendor/bootstrap.min.js"></script>			
			<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
  			<script src="js/easing.min.js"></script>			
			<script src="js/hoverIntent.js"></script>
			<script src="js/superfish.min.js"></script>	
			<script src="js/mn-accordion.js"></script>
			<script src="js/jquery.ajaxchimp.min.js"></script>
			<script src="js/jquery.magnific-popup.min.js"></script>	
			<script src="js/owl.carousel.min.js"></script>						
			<script src="js/jquery.nice-select.min.js"></script>	
    		<script src="js/jquery.circlechart.js"></script>								
			<script src="js/mail-script.js"></script>	
			<script src="js/main.js"></script>	
		</body>
</html>