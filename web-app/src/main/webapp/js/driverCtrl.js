function addDriver(){

    $.ajax({
        url : 'addDriverValidation',
        data : {
            name : $('#name').val(),
            surname : $('#surname').val(),
            phone : $('#phone').val(),
            carBrand : $('#car-brand').val(),
            carModel : $('#car-model').val(),
            licensePlate : $('#license-plate').val()
        },
        success : function(responseText) {
            console.log("response "+responseText);
            console.log(responseText.length);
            if (responseText.length == 3) {
                $.ajax({
                    type: 'POST',
                    url: "add-driver",
                    data: {
                        name : $('#name').val(),
                        surname : $('#surname').val(),
                        phone : $('#phone').val(),
                        carBrand : $('#car-brand').val(),
                        carModel : $('#car-model').val(),
                        licensePlate : $('#license-plate').val()
                    },
                    dataType: "text",
                    success: function (resultData) {
                            swal('Great job!', 'New driver has been added', 'success');
                            $('#name').val('');
                            $('#surname').val('');
                            $('#phone').val('');
                            $('#car-brand').val('');
                            $('#car-model').val('');
                            $('#license-plate').val('');
                            window.location.replace("http://localhost:8080/jjdz5magicy/drivers")
                    },
                    error: function (resultData) {
                        console.log(resultData);
                    }
            });
            }
            else{
                swal({
                        title: '<small style="width: 1200px;">This driver can not be added!</small>',
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