<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.connection.Dbconn"%>

<%@page import="com.algo.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@ page import="com.connection.Dbconn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Mobile Specific Meta -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
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

<link
	href="https://fonts.googleapis.com/css?family=Poppins:100,200,400,300,500,600,700"
	rel="stylesheet">
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
 <script type="text/javascript" src="jsnew/jquery-1.9.1.min.js"></script>
   
        <script src="jsnew/highcharts.js"></script>

<script>
    <%        
    DecimalFormat df = new DecimalFormat("#.##");
    Connection con;
    ArrayList<String> acc_values=new ArrayList<>();
    ArrayList<String> id_values=new ArrayList<>();
    int id=0;
	int pid=1;
	String name="";
	String product_info="";
	
	for(id=0;id<Dbconn.Predicted_Demand.size();id++)
	{
		
		String[]d1=Dbconn.Predicted_Demand.get(id).split("#");
		
		id_values.add(d1[0]);
		acc_values.add(d1[1]);
		 }
    
	String  arr11=id_values.toString().replace("[", "").replace("]", "")
		    .replace(", ", "','");//x
		    System.out.println(arr11);
		 String  arr22=acc_values.toString().replace("[", "").replace("]", "")
		    .replace(", ", ",");//y 
		    System.out.println(arr22);
    %>
    

$(function () {
    $('#containerg').highcharts({
    	chart: {
            type: 'column'
        },
    	title: {
            text: 'System Product Demand Info',
            x: -20 
        },
        subtitle: {
        	text: 'Parameters: X-Axies: Product Demand Info',
            x: -20
        },
        xAxis: {
        	title: {
                text: 'Product Name Info'
            },
            categories: ['<%=arr11%>']
        },
        yAxis: {
            title: {
                text: 'Product Demand Percentage %'
            },
            plotLines: [{
                value: 0,
                width: 0.5,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: ''
        },
        legend: {
        	
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: 'Product Name',
            data: [<%=arr22%>]

        }]
    });
});

</script>
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
				<jsp:include page="Amenu.jsp"></jsp:include>
			</ul>
			</nav>
			<!-- #nav-menu-container -->
		</div>
	</div>
	</header>
	<!-- #header -->

	<!-- Start testomial Area -->
	<section class="testomial-area section-gap" id="testimonail">
	<div class="container">
		<div class="row d-flex justify-content-center">
			<div style="width: 100%">
				<div class="title text-center">
					<h1 class="mb-10 text-white">Search Product</h1>
					<br>
				</div>

				<div class="form-group" style="width: 100%">
					

					<div id="containerg" style="min-width: 310px; height: 500px; max-width: 800px; margin: 0 auto"></div>
					
					
				</div>


			</div>
		</div>
	</div>
	</section>
	<!-- End testomial Area -->



	<!-- start footer Area -->
	<footer class="footer-area" style="height:20px;">
	<p class="col-lg-8 col-sm-12 footer-text m-0">
		Copyright &copy;
		<script>
			document.write(new Date().getFullYear());
		</script>
		All rights reserved | This template is made with <i
			class="fa fa-heart-o" aria-hidden="true"></i> by <a
			href="https://colorlib.com" target="_blank">Colorlib</a>
	</p>

	</footer>
	<!-- End footer Area -->

	
</body>
</html>