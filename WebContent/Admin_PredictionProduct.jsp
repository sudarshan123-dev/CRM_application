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
				<jsp:include page="Adminmenu.jsp"></jsp:include>
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
					<form role="form" action="Prediction_data" method="post">

						<table border="1" width="100%">


							<tr>
								<td style="width: 20%"><label for="txt_Pmanufacture"
									style="color: White; font-size: 18px;">Starts Date</label></td>
								<td style="width: 25%"><input type="date"
									class="form-control" name="startDate" id="startDate"
									>
									</td>
									<td style="width: 20%"><label for="txt_Pmanufacture"
									style="color: White; font-size: 18px;">End Date</label></td>
								<td style="width: 25%"><input type="date"
									class="form-control" name="endDate" id="endDate"
									>
									</td>
									<td style="width: 25%" colspan="2" align="center"><input
									type="submit" class="btn btn-primary" name="btn_Submit"
									id="btn_Submit" Value="Search Product"></td>
							</tr>
						
	       <tr>
			<th ><label for="txt_Pid" style="color:White;font-size:18px;">Sr. Id.:</label></th>
			<th colspan="2" ><label for="txt_Pid" style="color:White;font-size:18px;">Product Name:</label></th>
		    <th colspan="2"><label for="txt_Pid" style="color:White;font-size:18px;">Product Demand(%):</label></th>
		    
		    </tr>
	<%
	int id=0;
	int pid=1;
	String name="";
	String product_info="";
	
	for(id=0;id<Dbconn.Predicted_Demand.size();id++)
	{
		
		String[]d1=Dbconn.Predicted_Demand.get(id).split("#");
		
		 name=d1[0];
		 product_info=d1[1];
		
	
		
	
		 
%>
			<tr>
			<td class="col-md-1"><label for="txt_Pid" style="color:Black;font-size:18px;"><%=pid %></label></td>
			
			<td colspan="2"><label for="txt_Pid" style="color:Black;font-size:18px;"><%=name %></label></td>
			<td colspan="2"><label for="txt_Pid" style="color:Black;font-size:18px;"><%=product_info %></label></td>
			
		    </tr>
<%
pid++;
	}
	%>
			</table>
					</form>

					
					
					<script>
        const startDateInput = document.getElementById('startDate');
        const endDateInput = document.getElementById('endDate');

        startDateInput.addEventListener('change', function() {
            // Set minimum value of endDateInput to the value of startDateInput
            const selectedStartDate = new Date(this.value);
            const formattedDate = selectedStartDate.toISOString().split('T')[0];
            endDateInput.min = formattedDate;
        });

        endDateInput.addEventListener('change', function() {
            if (new Date(this.value) < new Date(startDateInput.value)) {
                alert('End date cannot be before start date.');
                this.value = ''; // Reset the invalid end date
            }
        });
    </script>
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

	<script src="js/vendor/jquery-2.2.4.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script src="js/vendor/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
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