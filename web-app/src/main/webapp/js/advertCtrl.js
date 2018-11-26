function addAdvert(){

    $.ajax({
        url : 'addAdvertAjax',
        data : {
            startCity : $('#startCity').val(),
            startStreet : $('#startStreet').val(),
            startTime : $('#startTime').val(),
            endCity : $('#endCity').val(),
            endStreet : $('#endStreet').val(),
            endTime : $('#endTime').val(),
            pickUpCity : $('#pickUpCity').val(),
            pickUpStreet : $('#pickUpStreet').val(),
            pickUpTime : $('#pickUpTime').val(),
            date : $('#date').val()
        },
        success : function(responseText) {
            console.log("response "+responseText);
            console.log(responseText.length);
            if (responseText.length == 3) {
                $.ajax({
                    type: 'POST',
                    url: "add-advert",
                    data: {
                        startCity: $('#startCity').val(),
                        startStreet: $('#startStreet').val(),
                        startTime: $('#startTime').val(),
                        endCity: $('#endCity').val(),
                        endStreet: $('#endStreet').val(),
                        endTime: $('#endTime').val(),
                        pickUpCity: $('#pickUpCity').val(),
                        pickUpStreet: $('#pickUpStreet').val(),
                        pickUpTime: $('#pickUpTime').val(),
                        date: $('#date').val()
                    },
                    dataType: "text",
                    success: function (resultData) {
                            swal('Great job!', 'Your advert has been added', 'success');
                            $('#startCity').val('');
                            $('#startStreet').val('');
                            $('#startTime').val('');
                            $('#endCity').val('');
                            $('#endStreet').val('');
                            $('#endTime').val('');
                            $('#pickUpCity').val('');
                            $('#pickUpStreet').val('');
                            $('#pickUpTime').val('');
                            $('#date').val('');

                    },
                    error: function (resultData) {
                        console.log(resultData);
                    }
            });
            }
            else{
                swal({
                        title: '<small style="width: 1200px;">Your advert can not be added!</small>',
                        text: '<label class="title">'+responseText+'</label>',
                        confirmButtonText: "OK",
                        html: responseText
                    },
                    function(isConfirm) {

                    });
            }
        },
        error: function (responseText) {
            console.log(responseText);
        }
    });

}