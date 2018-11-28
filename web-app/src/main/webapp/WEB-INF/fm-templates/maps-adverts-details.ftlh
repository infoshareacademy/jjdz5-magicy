<!doctype html>
<html lang="en" class="full-height">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>goTogether</title>

    <script src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.13.0/umd/popper.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="css/overall.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="sytlesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.0/bootstrap-table.min.css">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/css/mdb.min.css" rel="stylesheet">

    <style>
        #map {
            height: 100%;
            width: 100%;
        }
        .margin{
            margin-bottom:40px;
            margin-top:20px;
        }
        .separator-left {
            border-left: 1px solid rgba(0,0,0,.1);
        }
    </style>
</head>
<body>
<#assign apiKey = API_KEY?first>
<#assign advert = DETAIL_ADVERT?first>
<div class="container">
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark indigo">
        <a class="navbar-brand" href="home"><img src="img/logo.png" width="40"
                                                 height="40" class="display-inline-block" alt=""><strong>    goTogether</strong></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="home">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="maps-adverts">Adverts</a>
                    </li>
                    <li class="nav-item">
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
            <h3>Advert details</h3>
        </div>
    </div>

    <div class="row">
        <div class="col-3">
            <div class="alert alert-primary text-center">
                <h3>${advert.date}</h3>
            </div>
        </div>
        <div class="col-6"></div>
        <div class="col-3">
            <a href="maps-adverts" class="btn btn-primary float-right">Go back</a>
        </div>
    </div>
    
    <div class="row">

        <div class="col-6">
            <div class="card">
                <div class="card-header text-center">
                    <h3 class="text-muted">Departure</h3>
                </div>
                <div class="card-body">
                    <h5 style="color: #747373">${advert.startAddress.city}</h5>
                    <h5 style="color: #747373">${advert.startAddress.streetName} ${advert.startAddress.streetNumber}</h5>
                    <#if advert.startAddress.info?length &gt; 0>
                        <h5 style="color: #747373">${advert.startAddress.info}</h5>
                    </#if>
                </div>
            </div>
        </div>

        <div class="col-6">
            <div class="card">
                <div class="card-header text-center">
                    <h3 class="text-muted">Destination</h3>
                </div>
                <div class="card-body">
                    <h5 style="color: #747373">${advert.endAddress.city}</h5>
                    <h5 style="color: #747373">${advert.endAddress.streetName} ${advert.endAddress.streetNumber}</h5>
                    <#if advert.endAddress.info?length &gt; 0>
                        <h5 style="color: #747373">${advert.endAddress.info}<h5>
                    </#if>
                </div>
            </div>
        </div>

    </div>

    <div class="row margin">

        <div class="col-6">
            <div class="card">
                <div class="card-header text-center">
                    <h3 class="text-muted">Pick up points</h3>
                </div>
                <div class="card-body">
                    <h5 style="color: #747373">Free seats: 0/0</h5><a href="#" class="btn btn-primary">Reserve</a>
                </div>
            </div>
        </div>

        <div class="col-6">
            <div class="card">
                <div class="card-header text-center">
                    <h3 class="text-muted">Driver</h3>
                </div>
                <div class="card-body">
                    <h5 style="color: #747373">${advert.driver.name} ${advert.driver.surname}</h5>
                    <h5 style="color: #747373">${advert.driver.phoneNumber}</h5>
                    <hr style="margin: 10px">
                    <#assign car = advert.driver.cars?first>
                    <h5 style="color: #747373">${car.carBrand} ${car.carModel}</h5>
                    <h5 style="color: #747373">${car.licensePlate}</h5>
                </div>
            </div>
        </div>
    </div>

    <div class="row margin">
        <div class="col-12">
            <div class="card">
                <div class="card-header text-center">
                    <h3 class="text-muted">Route</h3>
                </div>
                <div class="card-body">
                    <div id="map" style="width: 100%; min-height: 720px"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="row margin"></div>
</div>

<#include "footer.ftlh">

<#setting locale="en_US">
<#assign sLat = advert.startAddress.latitude?string["0.#######"]>
<#assign sLng = advert.startAddress.longitude?string["0.#######"]>
<#assign eLat = advert.endAddress.latitude?string["0.#######"]>
<#assign eLng = advert.endAddress.longitude?string["0.#######"]>

<script>
    function initMap() {
        var directionsDisplay = new google.maps.DirectionsRenderer;
        var directionsService = new google.maps.DirectionsService;
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 14,
            center: {lat: 54.403859, lng: 18.604555},
            disableDefaultUI: true,
            disableDoubleClickZoom: true,
            draggable: true,
            fullscreenControl: false,
            streetViewControl: false,
            scrollwheel: false,
            zoomControl: true
        });
        directionsDisplay.setMap(map);

        calculateAndDisplayRoute(directionsService, directionsDisplay);
    }

    function calculateAndDisplayRoute(directionsService, directionsDisplay) {
        directionsService.route({
            origin: {lat: ${sLat}, lng: ${sLng}},
            destination: {lat: ${eLat}, lng: ${eLng}},
            travelMode: google.maps.TravelMode["DRIVING"]
        }, function(response, status) {
            if (status == 'OK') {
                directionsDisplay.setDirections(response);
            } else {
                window.alert('Directions request failed due to ' + status);
            }
        });
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=${apiKey}&callback=initMap">
</script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/js/mdb.min.js"></script>
</body>
</html>
