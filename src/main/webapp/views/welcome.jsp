<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<head>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/cerulean/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/style.css">
</head>
</head>

<body>

	<%@include file="struttura/header.jsp"%>

	<!-- Container per Menu e CorpoPrincipale -->
	<div id="mainContainer">

		<!-- Menu -->
		<div id="menu">
			<%@include file="struttura/menu.jsp"%>
		</div>

		<!-- CorpoPrincipale -->
		<div id="corpoPrincipale">
			<%@include file="struttura/corpoPrincipale.jsp"%>
		</div>

	</div>

	<%@include file="struttura/footer.jsp"%>
</body>
