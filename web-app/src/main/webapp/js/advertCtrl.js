    $('#add_button').bind("click", function() {
       checkData();
        return false;
    });

    function checkData(){
    var infolog = [];
        setTimeout(function () {
            $('input[required="required"]').each(function () {
                if ($(this).val() === '') {
                    console.log($(this));
                    infolog = 'NOK';
                }
            });
            if (infolog.length>0) {
                swal('Your advert can not be added', 'enter all required data', 'error');
                $('#add_button').unbind();
                $('#add_button').click();
                $('#add_button').bind("click", function() {
                    checkData();
                    return false;
                });

                return true;
            } else {
            $.post('AddAdvertServlet', {
                            }, function(responseText) {
                            if(responseText == 'OK'){
                            swal("Well done!", 'Your advert has been added', 'success');
                            $('#add_button').unbind();
                            $('#add_button').click();
                            }
                            else{
                            swal("Rejestracja dokumentu w zafakg", 'Dane nagłówkowe dokumentu założone z sukcesem w bazie zafakg pod numerem'+response.data, 'success');
                            return false;
                            }
                             return false;
                            });
                }
        }, 100)
    }