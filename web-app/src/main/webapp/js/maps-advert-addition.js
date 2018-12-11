
var originPlaceIdInput = document.getElementById("origin-place-id");
var originPreciseInput = document.getElementById("origin-precise");
var destinationPlaceIdInput = document.getElementById("destination-place-id");
var destinationPreciseInput = document.getElementById("destination-precise");
var originTimeInput = document.getElementById("origin-time");
var destinationTimeInput = document.getElementById("destination-time");
var destinationTime;
var originPlace;
var destinationPlace;
var isBeforeRouteCounting = true;
var userModifiedDepartureTime = false;
var marker;
var routeDuration;

function addEventListeners() {
    originTimeInput.addEventListener("input", departureTimeListener);
    destinationTimeInput.addEventListener("input", destinationTimeListener);

}

window.onload = addEventListeners;

function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        mapTypeControl: false,
        center: {lat: 54.432617, lng: 18.572935},
        zoom: 12,
        disableDefaultUI: true,
        disableDoubleClickZoom: true,
        draggable: true,
        fullscreenControl: false,
        keyboardShortcuts: false,
        streetViewControl: false,
        scrollwheel: false,
        zoomControl: true
    });

    new AutocompleteDirectionsHandler(map);
}

function departureTimeListener() {
    if (originTimeInput === "") {
        userModifiedDepartureTime = false;
    } else {
        userModifiedDepartureTime = true;
    }
}

function destinationTimeListener() {
    destinationTime = moment(destinationTimeInput.value, "HH:mm");
    if (destinationTimeInput !== undefined || destinationTimeInput !== "") {
        countApproximateDepartureTime();
    }
}

/**
 * @constructor
 */
function AutocompleteDirectionsHandler(map) {
    this.map = map;

    this.originPlaceId = null;
    this.destinationPlaceId = null;
    this.travelMode = 'DRIVING';
    var addressesCard = document.getElementById('addresses-card');
    var rightSideCards = document.getElementById('right-side-cards');
    var controls = document.getElementById('controls');
    var originAddress = document.getElementById('origin-address');
    var destinationAddress = document.getElementById('destination-address');
    var submitButton = document.getElementById('submitButton');
    this.directionsService = new google.maps.DirectionsService;
    this.directionsDisplay = new google.maps.DirectionsRenderer;

    this.directionsDisplay.setMap(map);
    var originAutocomplete = new google.maps.places.Autocomplete(
        originAddress);
    var destinationAutocomplete = new google.maps.places.Autocomplete(
        destinationAddress);

    this.setupPlaceChangedListener(originAutocomplete, 'ORIG', map);
    this.setupPlaceChangedListener(destinationAutocomplete, 'DEST', map);

    this.map.controls[google.maps.ControlPosition.TOP_LEFT].push(addressesCard);
    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(rightSideCards);
    map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(controls);
    this.map.controls[google.maps.ControlPosition.BOTTOM_CENTER].push(submitButton);
}

AutocompleteDirectionsHandler.prototype.setupPlaceChangedListener = function(autocomplete, mode, map) {
    var me = this;
    marker = new google.maps.Marker({
        map: map
    });
    autocomplete.bindTo('bounds', this.map);
    autocomplete.addListener('place_changed', function() {
        var place = autocomplete.getPlace();
        if (!place.place_id) {
            window.alert("Please select an option from the dropdown list.");
            return;
        }
        if (mode === 'ORIG') {
            if (isBeforeRouteCounting) {
                map.setCenter(place.geometry.location);
                map.setZoom(17);
                marker.setPosition(place.geometry.location);
                marker.setVisible(true);
            } else {
                marker.setVisible(false);
            }
            me.originPlaceId = place.place_id;
            originPlace = place;
        } else {
            if (isBeforeRouteCounting) {
                map.setCenter(place.geometry.location);
                map.setZoom(17);
                marker.setPosition(place.geometry.location);
                marker.setVisible(true);
            } else {
                marker.setVisible(false);
            }
            me.destinationPlaceId = place.place_id;
            destinationPlace = place;
        }
        me.route();
    });

};

AutocompleteDirectionsHandler.prototype.route = function() {
    if (!this.originPlaceId || !this.destinationPlaceId) {
        return;
    }
    var me = this;

    if (this.originPlaceId !== this.destinationPlaceId) {
        fillOriginInputFields(originPlace);
        fillDestinationInputFields(destinationPlace);
        this.directionsService.route({
            origin: {'placeId': this.originPlaceId},
            destination: {'placeId': this.destinationPlaceId},
            travelMode: this.travelMode
        }, function (response, status) {
            if (status === 'OK') {
                marker.setVisible(false);
                routeDuration = response.routes[0].legs[0].duration.value;
                isBeforeRouteCounting = false;
                me.directionsDisplay.setDirections(response);
                if (destinationTimeInput !== undefined || destinationTimeInput !== "") {
                    countApproximateDepartureTime();
                }
            } else {
                window.alert('Directions request failed due to ' + status);
            }
        });
    } else {
        window.alert('Departure and destination points cannot be the same.');
    }
};

function fillOriginInputFields(place) {
    originPlaceIdInput.value = place.place_id;
    var components = place.address_components;
    var precise;
    for (var i = 0; i < components.length; i++) {
        if (components[i].types[0] === "street_number") {
            precise = components[i].short_name;
        }
        if (precise === undefined) {
            originPreciseInput.value = "";
        } else {
            originPreciseInput.value = precise;
        }
    }
}

function fillDestinationInputFields(place) {
    destinationPlaceIdInput.value = place.place_id;
    var components = place.address_components;
    var precise;
    for (var i = 0; i < components.length; i++) {
        if (components[i].types[0] === "street_number") {
            precise = components[i].short_name;
        }
        if (precise === undefined) {
            destinationPreciseInput.value = "";
        } else {
            destinationPreciseInput.value = precise;
        }
    }
}

function countApproximateDepartureTime() {
    try {
        if (!isBeforeRouteCounting && destinationTime.isValid()) {
            var timeSuggestionField = document.getElementById("origin-time-suggestion");
            var timeInputField = document.getElementById("origin-time");
            var destinationTimeValue = moment(destinationTimeInput.value, "HH:mm");
            var departureTime = destinationTimeValue.subtract(routeDuration, "seconds").format("HH:mm").toString();
            timeSuggestionField.value = departureTime;
            if (!userModifiedDepartureTime) {
                timeInputField.value = departureTime;
            }
            if (timeInputField.value === "") {
                userModifiedDepartureTime = false;
                timeInputField.value = departureTime;
            }
        }
    } catch (e) {
        console.log("Enter arrival time.")
    }
};
