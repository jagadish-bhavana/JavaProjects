
function setTimer() {
   window.setTimeout("show_data();",5000);
}
function show_data(){
   //Append the id (just a simple count) to the requestURL
   var requestURL = "http://www.sitename.com/getGpsPoints.php";
   var count = document.getElementById("counter").value;
   count = parseInt(count, 10);
   var queries = "?id=" + count;
   var url = requestURL + queries;
   //Increment the hidden counter variable
   document.getElementById("counter").value = count+1;
   var request = GXmlHttp.create();
   request.open("GET", url, true);
   request.onreadystatechange = function() {
	  if (request.readyState == 4) {
		 var xmlDoc = request.responseXML;
		 var markers = xmlDoc.documentElement.getElementsByTagName("marker");
		 for (var i = 0; i < markers.length; i++) {
			var point = new GPoint(parseFloat(markers[i].getAttribute("lat")), parseFloat(markers[i].getAttribute("lng")));
			// Draw the MapMarker
			var mapMarker = new GMarker(point);
			map.addOverlay(mapMarker);
		 }
		 // Recenter the map
		 map.centerAtLatLng(point);
	  }
   }
   request.send(null);
   //Reset the timer so that the page keeps refreshing itself
   setTimer();
}


