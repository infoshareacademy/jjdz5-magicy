<!doctype html>
<html lang="en" class="full-height">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>goTogether</title>
    <link rel="stylesheet" href="css/overall.css">
    <link rel="stylesheet" href="css/maps-adverts.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/css/mdb.min.css" rel="stylesheet">
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
    <style>
        input:disabled {
            color: #000000 !important;
            background-color: #ffffff !important;
        }
    </style>
</head>

<body>

<#assign advert = DETAIL_ADVERT?first>
<script>
    $(document).ready(function(){
    document.getElementById("2nav").classList.add('active');
    });
</script>
<div class="container">

    <div id="loader">
        <div class="load"></div>
    </div>

    <#include "menu.ftlh">

    <div class="row mt-3">
        <div class="col-6">
            <button class="btn button" disabled>Advert details</button>
        </div>
        <div class="col-6">
            <a class="btn button float-right" href="/jjdz5-magicy/maps-adverts">
                Go back
                <i class="fas fa-undo-alt"></i>
            </a>
        </div>
    </div>

    <div class="row mt-1 mb-5">
        <div class="col-12">

            <div id="addresses-card" class="card controls-card bg-primary text-white expanded">
                <a><div id="addresses-header" class="card-header" role="button"
                        data-toggle="collapse" data-target="#collapseAddresses" aria-expanded="false"
                        aria-controls="collapseAddresses">Addresses
                </div></a>
                <div id="collapseAddresses" class="collapse show" aria-labelledby="addresses-header">
                    <div class="card-body">
                        <a><i id="cardExpander" class="fas fa-exchange-alt float-right"></i></a>
                        <div id="origin-address-label" class="card-title card-label-first">Departure:</div>
                        <input id="origin-address" class="form-control" type="text"
                               placeholder="Please enter departure address." required disabled>
                        <div id="destination-address-label" class="card-title card-label">Destination:</div>
                        <input id="destination-address" class="form-control" type="text"
                               placeholder="Please enter destination address." required disabled>
                        <#if advert.startAddressInfo?length &gt; 0>
                            <div id="origin-address-info-label" class="card-title card-label">Additional information form departure point:</div>
                            <input id="origin-address-info" class="form-control" type="text"
                                   name="startAddressInfo" placeholder="Departure additional information."
                                   disabled value="${advert.startAddressInfo}">
                        </#if>
                        <#if advert.endAddressInfo?length &gt; 0>
                            <div id="destination-address-info-label" class="card-title card-label">Additional information for destination point:</div>
                            <input id="destination-address-info" class="form-control mt-1" type="text"
                                   name="endAddressInfo" placeholder="Destination additional information."
                                   disabled value="${advert.endAddressInfo}">
                        </#if>
                    </div>
                </div>
            </div>

            <div id="right-side-cards">

                <div id="time-card" class="card controls-card bg-primary text-white panel">
                    <a role="button" data-toggle="collapse" data-target="#collapseTime"
                       aria-expanded="false" aria-controls="collapseTime" data-parent="#right-side-cards">
                        <div id="time-header" class="card-header">Time</div></a>
                    <div id="collapseTime" class="collapse show" aria-labelledby="time-header">
                        <div class="card-body">
                            <div id="origin-time-label" class="card-title card-label-first">Estimated<br>departure time:</div>
                            <input id="origin-time" class="form-control low-width-inputs" type="time" name="startTime" required disabled>
                            <div id="destination-time-label" class="card-title card-label">Desired<br>arrival time:</div>
                            <input id="destination-time" class="form-control low-width-inputs" type="time" name="endTime" required disabled>
                            <div id="date-label" class="card-title card-label">Date:</div>
                            <input id="date" class="form-control low-width-inputs" type="date" name="date" required disabled>
                        </div>
                    </div>
                </div>

                <div id="driver-card" class="card controls-card bg-primary text-white panel">
                    <a role="button" data-toggle="collapse" data-target="#collapseDriver"
                       aria-expanded="false" aria-controls="collapseDriver" data-parent="#right-side-cards">
                        <div id="driver-header" class="card-header">Driver</div></a>
                    <div id="collapseDriver" class="collapse show" aria-labelledby="driver-header">
                        <div class="card-body">
                            <div class="card-label">${advert.driver.name} ${advert.driver.surname}</div>
                            <div class="card-label">${advert.driver.phoneNumber}</div>
                            <#assign car = advert.driver.cars?first>
                            <div class="card-label">${car.carBrand} ${car.carModel}</div>
                            <div class="card-label">${car.licensePlate}</div>
                        </div>
                    </div>
                </div>

            </div>

            <div id="controls">
                <button id="show-cards" type="button" class="btn button"
                        onclick="$('.collapse').collapse('show');">
                    <i class="fas fa-eye"></i>
                </button>
                <br>
                <button id="hide-cards" type="button" class="btn button"
                        onclick="$('.collapse').collapse('hide');">
                    <i class="fas fa-eye-slash"></i>
                </button>
            </div>

            <div class="card mt-3 mb-5">
                <div id="map"></div>
            </div>
            
        </div>
    </div>
</div>

<#include "footer.ftlh">

<#setting locale="en_US">
<#setting time_format="HH:mm">
<#assign sLat = advert.startAddress.latitude?string["0.#######"]>
<#assign sLng = advert.startAddress.longitude?string["0.#######"]>
<#assign sId = advert.startAddress.mapsPointId>
<#assign eId = advert.endAddress.mapsPointId>
<#assign eLat = advert.endAddress.latitude?string["0.#######"]>
<#assign eLng = advert.endAddress.longitude?string["0.#######"]>
<#assign sAddress = advert.startAddress.formattedAddress>
<#assign eAddress = advert.endAddress.formattedAddress>
<#assign sTime = advert.startTime?time>
<#assign eTime = advert.endTime?time>
<#assign date = advert.date>

<script>

    var originPlaceIdField = document.getElementById("origin-address");
    var destinationPlaceIdField = document.getElementById("destination-address");
    var originTimeField = document.getElementById("origin-time");
    var destinationTimeField = document.getElementById("destination-time");
    var dateField = document.getElementById("date");

    window.onload = function () {
        originPlaceIdField.value = "${sAddress}";
        destinationPlaceIdField.value = "${eAddress}";
        originTimeField.value = "${sTime}";
        destinationTimeField.value = "${eTime}";
        dateField.value = "${date}";
    };
    
    function initMap() {
        var addressesCard = document.getElementById('addresses-card');
        var rightSideCards = document.getElementById('right-side-cards');
        var controls = document.getElementById('controls');
        var directionsDisplay = new google.maps.DirectionsRenderer;
        var directionsService = new google.maps.DirectionsService;
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 12,
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

        map.controls[google.maps.ControlPosition.TOP_LEFT].push(addressesCard);
        map.controls[google.maps.ControlPosition.TOP_RIGHT].push(rightSideCards);
        map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(controls);

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
<script src="js/preloader.js"></script>
<script src="js/address-card-expander.js"></script>
<#assign apikey = API_KEY[0]>
<script src="https://maps.googleapis.com/maps/api/js?key=${apikey}&libraries=places&callback=initMap"
        async defer></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/js/mdb.min.js"></script>
</body>
</html>
