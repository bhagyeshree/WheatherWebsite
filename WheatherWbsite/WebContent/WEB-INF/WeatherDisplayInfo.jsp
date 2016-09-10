<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style>

body { 
background-image: url("http://localhost:8080/WheatherWbsite/images/bg.jpg"); 
background-repeat: no-repeat;
background-attachment:fixed;
}

#map {
        width: 380px;
        height: 200px;
      }
      
  #placeholder { width: 800px; height: 180px; position: relative; margin: 0 auto; }
.legend table, .legend > div { height: 50px !important; opacity: 1 !important; right: -145px; top: 10px; width: 150px !important; }
.legend table { border: 1px solid #555; padding: 5px; }
#flot-tooltip { font-size: 12px; font-family: Verdana, Arial, sans-serif; position: absolute; display: none; border: 2px solid; padding: 2px; background-color: #FFF; opacity: 0.8; -moz-border-radius: 5px; -webkit-border-radius: 5px; -khtml-border-radius: 5px; border-radius: 5px; }
 
</style>


<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WeatherDisplayInfo</title>


<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/flot/0.8.2/jquery.flot.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/flot/0.8.2/jquery.flot.symbol.min.js"></script>
<script type="text/javascript" src="http://raw.github.com/markrcote/flot-axislabels/master/jquery.flot.axislabels.js"></script>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/flot/0.8.2/jquery.flot.time.min.js"></script>
 
<script type="text/javascript">
//Rome, Italy

var max1 = ${max1};
var max2 = ${max2};
var max3 = ${max3};
var max4 = ${max4};
var max5 = ${max5};
var max6 = ${max6};
var max7 = ${max7};
var max8 = ${max8};
var max9 = ${max9};
var max10 = ${max10};
var max11 = ${max11};
var max12 = ${max12};


var min1 = ${min1};
var min2 = ${min2};
var min3 = ${min3};
var min4 = ${min4};
var min5 = ${min5};
var min6 = ${min6};
var min7 = ${min7};
var min8 = ${min8};
var min9 = ${min9};
var min10 = ${min10};
var min11 = ${min11};
var min12 = ${min12};

var day1 = "${day1}";
var day2 = "${day2}";
var day3 = "${day3}";
var day4 = "${day4}";
var day5 = "${day5}";
var day6 = "${day6}";
var day7 = "${day7}";
var day8 = "${day8}";
var day9 = "${day9}";
var day10 = "${day10}";
var day11 = "${day11}";
var day12 = "${day12}";


var d1 = [[1262304000000, max1 ], [1264982400000, max2], [1267401600000, max3], [1270080000000, max4], [1272672000000, max5], [1275350400000, max6], [1277942400000, max7], [1280620800000, max8], [1283299200000, max9], [1285891200000, max10], [1288569600000, max11], [1291161600000, max12]];
var d2 = [[1262304000000, min1 ], [1264982400000, min2], [1267401600000, min3], [1270080000000, min4], [1272672000000, min5], [1275350400000, min6], [1277942400000, min7], [1280620800000, min8], [1283299200000, min9], [1285891200000, min10], [1288569600000, min11], [1291161600000, min12]];

var cityName = "${ cityInfo.cityName }";
var data1 = [
    {label: cityName+", Max",  data: d1, points: { symbol: "circle", fillColor: "#74655e" }, color: '#74655e'},
    {label: cityName+", Min",  data: d2, points: { symbol: "diamond", fillColor: "#BA4A00" }, color: '#BA4A00'}

];
 
$(document).ready(function () {
    $.plot($("#placeholder"), data1, {
        xaxis: {
            mode: "time",
            tickSize: [1, "month"],
            monthNames: [day1, day2, day3, day4, day5, day6, day7, day8, day9, day10, day11, day12],
            tickLength: 0,
            axisLabel: 'Month',
            axisLabelUseCanvas: true,
            axisLabelFontSizePixels: 12,
            axisLabelFontFamily: 'Verdana, Arial, Helvetica, Tahoma, sans-serif',
            axisLabelPadding: 5
        },
        yaxis: {
            axisLabel: 'Temperature (C)',
            axisLabelUseCanvas: true,
            axisLabelFontSizePixels: 12,
            axisLabelFontFamily: 'Verdana, Arial, Helvetica, Tahoma, sans-serif',
            axisLabelPadding: 5
        },
        series: {
            lines: { show: true },
            points: {
                radius: 3,
                show: true,
                fill: true
            },
        },
        grid: {
            hoverable: true,
            borderWidth: 1
        },
        legend: {
            labelBoxBorderColor: "none",
                position: "right"
        }
    });
 
    function showTooltip(x, y, contents, z) {
        $('<div id="flot-tooltip">' + contents + '</div>').css({
            top: y - 50,
            left: x - 90,
            'border-color': z,
        }).appendTo("body").fadeIn(200);
    }
 
    function getMonthName(numericMonth) {
        var monthArray = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];
        var alphaMonth = monthArray[numericMonth];

        return alphaMonth;
    }
 
    function convertToDate(timestamp) {
        var newDate = new Date(timestamp);
        var dateString = newDate.getDay();
        var monthName = getMonthName(dateString);
 
        return monthName;
    }
 
    var previousPoint = null;
 
    $("#placeholder").bind("plothover", function (event, pos, item) {
        if (item) {
            if ((previousPoint != item.dataIndex) || (previousLabel != item.series.label)) {
                previousPoint = item.dataIndex;
                previousLabel = item.series.label;
 
                $("#flot-tooltip").remove();
 
                var x = convertToDate(item.datapoint[0]),
                y = item.datapoint[1];
                z = item.series.color;
 
                showTooltip(item.pageX, item.pageY,
                    "<div align='center'> <b>" + item.series.label + "</b><br /> " +""+ "" + y + "°</div>",
                    z);
            }
        } else {
            $("#flot-tooltip").remove();
            previousPoint = null;
        }
    });
});
</script>



</head>
<body>
<br>
<br>


 <div class="container">
<div class="jumbotron">
  <h1>${ cityInfo.cityName }, ${ cityInfo.country } </h1>
  
  <form class="form-inline">
  <div class="form-group">
    <input type="text" name="newLocation" class="form-control" id="exampleInputName" placeholder="Type The City Name">
  </div>
  <button type="submit" class="btn btn-default">Change Location</button>
</form>


</div>

<div align="center"> 
<table>
<tr>


<td align="center" width= "200px" >
<div class="well">
   <h3>${details.weatherMain} </h3>
    <h5>(${details.description})</h5>
   <h1> <b>${current}</b>°<small><a href="WeatherDisplayInfo?newLocation=${newLocation}&m=C">C</a> |</small>°<small><a href="WeatherDisplayInfo?newLocation=${newLocation}&m=F">F</a></small></h1> 
   Max : ${max} , Min : ${min}
 
</div>
</td>


<td align="center" width= "30px">
</td>


<td align="Left" width= "200px">
<div class="well">
   <h4><b>Details :</b></h4>
   Humidity : ${details.humidity}%
   <br>
   Pressure : ${details.pressure} hPa
   <br>
   Speed : ${details.speed} meter/sec
   <br>
   Wind Degree : ${details.windDegree}°
   <br>
   Clouds : ${details.clouds}%
</div>
</td>

<td align="center" width= "30px">
</td>

<td align="Left" >
<div class="well">

 <div id="map"></div>
 
   <script>
      function initMap() {
    	var lat1 = ${cityInfo.cityLocationLatitude};
    	var longt = ${cityInfo.cityLocationLongitude};
        var mapDiv = document.getElementById('map');
        var map = new google.maps.Map(mapDiv, {
          center: {lat: lat1, lng: longt},
          zoom: 12     
        });
        
        var myLatLng = {lat: lat1, lng: longt};
        var cityName = "${ cityInfo.cityName }";
        var marker = new google.maps.Marker({
            position: myLatLng,
            map: map,
            title: cityName
          });    
      }
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?callback=initMap"
        async defer></script>
  
 
</div>
</td>

</tr>
</table>


<table>
<tr>
<td align="center"  >
<div class="well">

<div id="placeholder"></div>

</div>
</td>
</tr>
</table>
</div>
 </div>
 
 <br>

</body>
</html>