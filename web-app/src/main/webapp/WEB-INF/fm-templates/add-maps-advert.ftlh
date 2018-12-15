<!DOCTYPE html>
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
    <#include "google-log.ftlh">
</head>

<body id="top">
<script>
    $(document).ready(function(){
    document.getElementById("3nav").classList.add('active');
    });

        function showAddress(){
    $('.collapse').show();
    }

    function hideAddress(){
    $('.collapse').hide();
    }
</script>
<div class="container">

    <div id="loader">

        <div class="load"></div>
    </div>

    <#include "menu.ftlh">

    <form method="post" action="/jjdz5-magicy/add-maps-advert">

    <div class="row mt-3">
        <div class="col-6">
            <button type="submit" class="btn button">Add new advert</button>
        </div>
        <div class="col-6">
            <a class="btn button float-right" href="#info">
                Help
                <i class="fas fa-info-circle"></i>
            </a>
        </div>
    </div>

    <#if SUMMARY?has_content>

      <div id="summary" class="row mt-1">
          <div class="col-12">
              <div class="alert alert-summary">
                  <ul>
                      <#list SUMMARY as msg>
                          <li>${msg}</li>
                      </#list>
                  </ul>
              </div>
          </div>
      </div>

    </#if>

    <div class="row mt-1 mb-3">
        <div class="col-12">

            <div id="addresses-card" class="card controls-card bg-primary text-white">
                <a><div id="addresses-header" class="card-header" role="button"
                        data-toggle="collapse" data-target="#collapseAddresses" aria-expanded="false"
                        aria-controls="collapseAddresses">Addresses
                </div></a>
                <div id="collapseAddresses" class="collapse show" aria-labelledby="addresses-header">
                    <div class="card-body">
                        <a><i id="cardExpander" class="fas fa-exchange-alt float-right"></i></a>
                        <div id="origin-address-label" class="card-title card-label-first">Departure:</div>
                        <input id="origin-address" class="form-control" type="text"
                               placeholder="Please enter departure address." required>
                        <div id="destination-address-label" class="card-title card-label">Destination:</div>
                        <input id="destination-address" class="form-control" type="text"
                               placeholder="Please enter destination address." required>
                        <div id="addresses-info-label" class="card-title card-label">Additional information (not required):</div>
                        <input id="origin-address-info" class="form-control" type="text"
                               name="startInfo" placeholder="Departure additional information.">
                        <input id="destination-address-info" class="form-control mt-1" type="text"
                               name="endInfo" placeholder="Destination additional information.">
                        <div class="card-title card-label">
                            <span>Overall distance: </span>
                            <span id="overall-distance">0 km</span>
                        </div>
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
                            <input id="origin-time" class="form-control low-width-inputs" type="time" name="startTime" required>
                            <div id="origin-time-suggestion-label" class="card-title card-label">Recommended<br>departure time:</div>
                            <input id="origin-time-suggestion" class="form-control low-width-inputs text-white" type="time"
                                   style="background: rgba(0.0.0.1)" disabled>
                            <div id="destination-time-label" class="card-title card-label">Desired<br>arrival time:</div>
                            <input id="destination-time" class="form-control low-width-inputs" type="time" name="endTime" required>
                            <div id="date-label" class="card-title card-label">Date:</div>
                            <input id="date" class="form-control low-width-inputs" type="date" name="date" required>
                        </div>
                    </div>
                </div>

                <div id="driver-card" class="card controls-card bg-primary text-white panel">
                    <a role="button" data-toggle="collapse" data-target="#collapseDriver"
                       aria-expanded="false" aria-controls="collapseDriver" data-parent="#right-side-cards">
                        <div id="driver-header" class="card-header">Your data:</div></a>
                    <div id="collapseDriver" class="collapse show" aria-labelledby="driver-header">
                        <div class="card-body">
                            <div class="card-body">
                                <#assign driver = DRIVER>
                                <div class="card-label">${driver.name} ${driver.surname}</div>
                                <div class="card-label">${driver.phoneNumber}</div>
                                <div class="card-label">${driver.car.carBrand}</div>
                                <div class="card-label">${driver.car.carModel}</div>
                                <div class="card-label">${driver.car.licensePlate}</div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <div id="hidden-inputs">
                <input id="origin-place-id" type="hidden" name="startMapsPointId">
                <input id="destination-place-id" type="hidden" name="endMapsPointId">
                <input id="origin-precise" type="hidden" name="startStreetNumber">
                <input id="destination-precise" type="hidden" name="endStreetNumber">
                <input id="route-modifiers" type="hidden" name="routeModifiers">
                <input id="driver-id" type="hidden" name="driverId" value="${driver.id}">
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

            <button type="submit" id="submitButton" class="btn button">Submit</button>

            <div class="card mt-3 mb-3">
                <div id="map"></div>
            </div>

        </div>
    </div>

    </form>

    <div class="row mb-5 pb-5">
        <div id="info" class="col-12">
            <div class="alert alert-help" role="alert">
                <h5>Addresses:</h5>
                <ul>
                    <li>When you are typing address, be sure to confirm it by selecting your choice from dropdown list.</li>
                    <li>Try to type address as precise as possible. Writing street number will be very helpful!</li>
                    <li>Remember that departure and destination address cannot be the same.</li>
                </ul>
                <h5>Route modifying:</h5>
                <ul>
                    <li>You can modify route generated for you. To achieve that drag route lines into streets you want to go through.</li>
                    <li>Modifying route will generate waypoints on it. Please remember that maximum quantity of these points is ${ROUTE_MODIFIERS_CONDITION?first}.</li>
                </ul>
                <h5>Time:</h5>
                <ul>
                    <li>When you enter your desired arrival time, after correct selection of departure and destination addresses, departure time will be calculated automatically.</li>
                    <li>Recommended departure time is just a suggestion. You can of course modify it by your own.</li>
                    <li>Remember that departure time cannot be earlier than ${TIME_CONDITION?first} hours from now!</li>
                </ul>
                <h5>Date:</h5>
                <ul>
                    <li>Chosen date cannot be earlier than today and no later than ${DATE_CONDITION?first} days.</li>
                </ul>
            </div>
            <div class="text-center mt-2">
                <a href="#top" style="color: #3f51b5 !important;">
                    <i class="fas fa-arrow-up"></i>
                    Back to top
                    <i class="fas fa-arrow-up"></i>
                </a>
            </div>
        </div>
    </div>

</div>

<#include "footer.ftlh">

<script src="js/preloader.js"></script>
<script src="js/address-card-expander.js"></script>
<script src="js/enter-submit-form-blocker.js"></script>
<script src="js/maps-advert-addition.js"></script>
<#assign apikey = API_KEY[0]>
<script src="https://maps.googleapis.com/maps/api/js?key=${apikey}&libraries=places&callback=initMap"
        async defer></script>

</body>
</html>