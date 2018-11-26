<!doctype html>
<html lang="en" class="full-height" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>goTogether</title>

    <link rel="stylesheet" href="css/overall.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/css/mdb.min.css" rel="stylesheet">
    <script src="https://unpkg.com/sweetalert2@7.17.0/dist/sweetalert2.all.js"></script>

    <style>
        .view {
            background-repeat: no-repeat;
            background-position: center;
            background-image: url("img/background.jpg");
            background-size: cover;
        }
        .imgCenter {
            display: block;
            margin: auto;
        }
        .margin{
            margin-bottom:40px;
            margin-top:20px;
        }
        .margin-t{
            margin-bottom:20px;
            margin-top:20px;
        }
        .margin-b{
            margin-bottom:40px;
        }

        /*TODO style listy miejsc od googla, za mała i niewidoczna*/

    </style>
</head>
<body>

  <div class="container">
     <nav class="navbar fixed-top navbar-expand-lg navbar-dark indigo">
         <a class="navbar-brand" href="home"><img src="img/logo.png" width="40"
                                                  hight="40" class="display-inline-block" alt=""><strong>    goTogether</strong></a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                  aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav mr-auto">
               <li class="nav-item">
                       <a class="nav-link" href="home">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="adverts">Adverts</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="add-maps-advert">Add advert</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="drivers">Drivers</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="add-driver">Add driver</a>
                    </li>
                  <li class="nav-item">
                      <a class="nav-link" href="about-us">About us</a>
                  </li>
              </ul>
          </div>
      </nav>
      <div class="row">
          <div class="mt-5 mb-5">
              <h3>Add new advert</h3>
          </div>
      </div>

      <#if SUMMARY?has_content>

      <div class="row">
          <div class="col-12">
              <#list SUMMARY as msg>
                  <h4>${msg}</h4>
              </#list>
          </div>
      </div>

      </#if>

      <form method="post" action="/jjdz5-magicy/add-maps-advert">
          <div class="row border-bottom"><div class="col-12"><h3 class="text-muted">From</h3></div></div>

          <div class="row margin-t">
              <div class="col-10 form-group">
                  <label for="start_autocomplete">Departure address:</label>
                  <input id="start_autocomplete" placeholder="Please enter your departure address here."
                            onFocus="geolocate()" type="text" class="form-control" required>
              </div>
              <div class="col-2 form-group">
                  <label for="startTime">At time (HH:mm):</label>
                  <input id="startTime" type="time" name="startTime"
                         placeholder="Enter departure time" class="form-control" required>
              </div>
          </div>
          <div class="row margin-b">
              <div class="col-12 form-group">
                  <label for="start_premise">Optional information about departure point.</label>
                  <input id="start_premise" name="startInfo"
                         placeholder="Please enter information (e.g. building name) here, if you want to add it."
                         type="text" class="form-control">
              </div>
          </div>

          <input type="hidden" id="start_route" name="startStreet">
          <input type="hidden" id="start_street_number" name="startStreetNumber">
          <input type="hidden" id="start_locality" name="startCity"><br>
          <input type="hidden" id="start_lat" name="startLatitude">
          <input type="hidden" id="start_lng" name="startLongitude">
          <input type="hidden" id="start_place_id" name="startMapsPointId">

        <div class="row border-bottom"><div class="col-12 center"><h3 class="text-muted">To</h3></div></div>
        <div class="row margin-t">
            <div class="col-10 form-group">
                <label for="end_autocomplete">Destination address:</label>
                <input id="end_autocomplete" placeholder="Please enter your destination address here."
                       onFocus="geolocate()" type="text" class="form-control" required>
            </div>
            <div class="col-2 form-group">
                <label for="endTime">At time (HH:mm):</label>
                <input id="endTime" type="time" name="endTime"
                       placeholder="Enter arrival time" class="form-control" required>
            </div>
        </div>

          <div class="row margin-b">
              <div class="col-12 form-group">
                  <label for="end_premise">Optional information about destination point.</label>
                  <input id="end_premise" name="endInfo"
                         placeholder="Please enter information (e.g. building name) here, if you want to add it."
                         type="text" class="form-control">
              </div>
          </div>

          <input type="hidden" id="end_route" name="endStreet">
          <input type="hidden" id="end_street_number" name="endStreetNumber">
          <input type="hidden" id="end_locality" name="endCity"><br>
          <input type="hidden" id="end_lat" name="endLatitude">
          <input type="hidden" id="end_lng" name="endLongitude">
          <input type="hidden" id="end_place_id" name="endMapsPointId">

          <div class="row">
              <div class="col-10 form-group text-muted">
                  <h3><label for="date">Date</label></h3>
                  <input id="date" type="date" name="date" class="form-control" required/>
              </div>

              <div class="col-2 form-group text-muted">
                  <h3><label for="driverId">Driver</label></h3>
                  <input id="driverId" type="text" name="driverId" class="form-control" required/>
              </div>
          </div>
          <div class="row margin pb-5">
              <div class="col-4"></div>
              <div class="col-3 imgCenter">
                  <button type="submit" class="btn btn-primary btn-block">Submit</button>
              </div>
              <div class="col-4"></div>
          </div>
    </form>
</div>

<#include "footer.ftlh">

  <script>

      var placeSearch, startAutocomplete, endAutocomplete;
      var componentForm = {
          street_number: 'short_name',
          route: 'long_name',
          locality: 'long_name',
          premise: 'long_name'
      };

      function initAutocomplete() {
          startAutocomplete = new google.maps.places.Autocomplete(
                  /** @type {!HTMLInputElement} */(document.getElementById('start_autocomplete')),
                  {types: ['geocode','establishment']});
          endAutocomplete = new google.maps.places.Autocomplete(
                  /** @type {!HTMLInputElement} */(document.getElementById('end_autocomplete')),
                  {types: ['geocode','establishment']});

          startAutocomplete.addListener('place_changed', fillInStartAddress);
          endAutocomplete.addListener('place_changed', fillInEndAddress);
      }

      function fillInStartAddress() {
          var place = startAutocomplete.getPlace();
          var prefix = "start_";

          for (var component in componentForm) {
              document.getElementById(prefix + component).value = '';
          }

          for (var i = 0; i < place.address_components.length; i++) {
              var addressType = place.address_components[i].types[0];
              if (componentForm[addressType]) {
                  var val = place.address_components[i][componentForm[addressType]];
                  document.getElementById(prefix + addressType).value = val;
              }
          }

          var lat = place.geometry.location.lat();
          document.getElementById(prefix + "lat").value = lat;

          var lng = place.geometry.location.lng();
          document.getElementById(prefix + "lng").value = lng;

          var placeGoogleId = place.place_id;
          document.getElementById(prefix + "place_id").value = placeGoogleId;

      }

      function fillInEndAddress() {
          var place = endAutocomplete.getPlace();
          var prefix = "end_";

          for (var component in componentForm) {
              document.getElementById(prefix + component).value = '';
          }

          for (var i = 0; i < place.address_components.length; i++) {
              var addressType = place.address_components[i].types[0];
              if (componentForm[addressType]) {
                  var val = place.address_components[i][componentForm[addressType]];
                  document.getElementById(prefix + addressType).value = val;
              }
          }

          var lat = place.geometry.location.lat();
          document.getElementById(prefix + "lat").value = lat;

          var lng = place.geometry.location.lng();
          document.getElementById(prefix + "lng").value = lng;

          var placeGoogleId = place.place_id;
          document.getElementById(prefix + "place_id").value = placeGoogleId;
      }

      function geolocate() {
          if (navigator.geolocation) {
              navigator.geolocation.getCurrentPosition(function(position) {
                  var geolocation = {
                      lat: position.coords.latitude,
                      lng: position.coords.longitude
                  };
                  var circle = new google.maps.Circle({
                      center: geolocation,
                      radius: position.coords.accuracy
                  });
                  startAutocomplete.setBounds(circle.getBounds());
              });
          }
      }

  </script>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.13.0/umd/popper.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/js/mdb.min.js"></script>
<#assign apikey = API_KEY[0]>
<script src="https://maps.googleapis.com/maps/api/js?key=${apikey}&libraries=places&callback=initAutocomplete" async defer></script>
</body>
</html>