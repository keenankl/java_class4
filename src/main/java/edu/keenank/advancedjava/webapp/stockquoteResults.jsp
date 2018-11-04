
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="search" class="edu.keenank.advancedjava.model.StockSearch" scope="session">
    <c:set target='${search}'  value='${sessionScope.get("search")}'/>
</jsp:useBean>

<html>
<head>
    <title>Stock Quote Search Result</title>
</head>
<body>

Here is the result of your stock search: <br />
<c:out value="${search.quoteStr}"/>

<p>
    To start another stock quote search, click on the following link: <a href="' + document.referrer + '">Go Back </a>
</p>

</body>
</html>