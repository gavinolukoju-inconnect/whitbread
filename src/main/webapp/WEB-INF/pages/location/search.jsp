<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />

<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load("current", {
        "packages":["map"],
        // Note: you will need to get a mapsApiKey for your project.
        // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
        "mapsApiKey": "AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY"
    });
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Lat', 'Long', 'Name'],
          <c:forEach items="${fourSquareDemoFormBean.venueList}" var="venue">
          [<c:out value = "${venue.latitude}"/>, <c:out value = "${venue.longitude}"/>,'<c:out value = "${venue.name}"/>'],
          </c:forEach>
        ]);

        var map = new google.visualization.Map(document.getElementById('map_div'));
        map.draw(data, {
          showTooltip: true,
          showInfoWindow: true
        });
      }

    </script>
  </head>

  <body>
    <form:form role="form" action="${ctx}/location/search" method="POST" commandName="fourSquareDemoFormBean">
    
        <spring:bind path="locationName">
		<div class="form-group">
		    <label>Enter a place (Place, Country Code e.g Reading, UK)</label>
		    <c:if test="${status.error}"><span class="alert-msg"><i class="icon-remove-sign"></i></c:if>
			    <form:input name="locationName" path="locationName" type="text" class="form-control" tabindex="1"/>
			<c:if test="${status.error}"></span></c:if>
		</div>
		</spring:bind>

		<input type="submit" value="Submit" class="btn btn-default">
		<input type="submit" value="Cancel" class="btn btn-default">

    </form:form>
  
    <div id="map_div" style="width: 400px; height: 300px"></div>
  </body>
</html>