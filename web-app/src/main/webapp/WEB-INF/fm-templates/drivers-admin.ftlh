<!doctype html>
<html lang="en" class="full-height">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>goTogether</title>

    <link rel="stylesheet" href="css/overall.css">
    <script src="js/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="sytlesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.0/bootstrap-table.min.css">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/css/mdb.min.css" rel="stylesheet">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <#include "google-log.ftlh">

    <style>
        .filterable {
            margin-top: 15px;
        }
        .filterable .panel-heading .pull-right {
            margin-top: -20px;
        }
        .filterable .filters input[disabled] {
            background-color: transparent;
            border: none;
            cursor: auto;
            box-shadow: none;
            padding: 0;
            height: auto;
        }
        .filterable .filters input[disabled]::-webkit-input-placeholder {
            color: #333;
        }
        .filterable .filters input[disabled]::-moz-placeholder {
            color: #333;
        }
        .filterable .filters input[disabled]:-ms-input-placeholder {
            color: #333;
        }
        .blue-column{
            background: #f2f2f2;
        }

    </style>
</head>
<body>
<script>
    $(document).ready(function(){
    document.getElementById("8nav").classList.add('active');
    $('#user_id').val(${user.id});

        $('.filterable .btn-filter').click(function(){
            var $panel = $(this).parents('.filterable'),
                    $filters = $panel.find('.filters input'),
                    $tbody = $panel.find('.table tbody');
            if ($filters.prop('disabled') == true) {
                $filters.prop('disabled', false);
                $filters.first().focus();
            } else {
                $filters.val('').prop('disabled', true);
                $tbody.find('.no-result').remove();
                $tbody.find('tr').show();
            }
        });

        $('.filterable .filters input').keyup(function(e){
            /* Ignore tab key */
            var code = e.keyCode || e.which;
            if (code == '9') return;
            /* Useful DOM data and selectors */
            var $input = $(this),
                    inputContent = $input.val().toLowerCase(),
                    $panel = $input.parents('.filterable'),
                    column = $panel.find('.filters th').index($input.parents('th')),
                    $table = $panel.find('.table'),
                    $rows = $table.find('tbody tr');
            /* Dirtiest filter function ever ;) */
            var $filteredRows = $rows.filter(function(){
                var value = $(this).find('td').eq(column).text().toLowerCase();
                return value.indexOf(inputContent) === -1;
            });
            /* Clean previous no-result if exist */
            $table.find('tbody .no-result').remove();
            /* Show all rows, hide filtered ones (never do that outside of a demo ! xD) */
            $rows.show();
            $filteredRows.hide();
            /* Prepend no-result row if all rows are filtered */
            if ($filteredRows.length === $rows.length) {
                $table.find('tbody').prepend($('<tr class="no-result text-center"><td colspan="'+ $table.find('.filters th').length +'">No result found</td></tr>'));
            }
        });
    });

    function deleteDriver(id){
    $('#id').val(id);
swal({
  title: "Are you sure?",
  text: "Do you want to delete driver?",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((willDelete) => {
  if (willDelete) {
$.ajax({
                    type: 'POST',
                    url: "drivers-admin",
                    data: {
                        id: $('#id').val()
                    },
                    dataType: "text",
                    success: function (resultData) {
                    $('#id').val('');
                    window.location.replace("http://localhost:8080/jjdz5-magicy/drivers-admin");
                    },
                    error: function (resultData) {
                        console.log(resultData);
                    }
            });
  } else {
    swal("Canceled!");
  }
});

    }

</script>

<div class="container">
    <#include "menu.ftlh">
    <div class="row">
        <div class="row mt-3">
            <form method="get" action="/jjdz5-magicy/adverts-admin">
                <div class="col-12" style="padding: 15px">
                    <button class="btn button" style="background-color: #3f51b5">Adverts</button>
                </div>
            </form>
        </div>
        <div class="row mt-3">
            <form method="get" action="/jjdz5-magicy/users-admin">
                <div class="col-12" style="padding: 15px">
                    <button class="btn button" style="background-color: #3f51b5">Users</button>
                </div>
            </form>
        </div>
        <div class="row mt-3">
                <div class="col-12" style="padding: 15px">
                    <button class="btn button" style="background-color: #999999">Drivers</button>
                </div>
        </div>
        <div class="row mt-3">
            <form method="get" action="/jjdz5-magicy/statistics-admin">
                <div class="col-12" style="padding: 15px">
                    <button class="btn button" style="background-color: #3f51b5">Statistics</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row mt-3">
        <div class="mt-5">
            <h3>Drivers</h3>
        </div>
    </div>

    <div class="row mt-3 mb-5">

        <div class="card" style="width:90%">
            <div class="panel panel-primary filterable">
                <table class="table table-bordered table-sm">
                    <thead>
                    <tr class="filters">
                        <th class="align-middle"><input style="text-align: center" type="text" class="form-control" placeholder="Name" disabled></th>
                        <th class="align-middle"><input style="text-align: center" type="text" class="form-control" placeholder="Surname" disabled></th>
                        <th class="align-middle"><input style="text-align: center" type="text" class="form-control" placeholder="Phone" disabled></th>
                        <th class="text-center"><button class="btn btn-filter button-functional" style="background-color: #2bbbad!important">
                            Filter
                        </button></th>
                    </tr>

                    </thead>
                    <tbody>
                    <#list DRIVERS as d>
                    <tr>
                        <td style="background: #cceeff; text-align: center" class="align-middle">${d.getName()}</td>
                        <td style="background: #cceeff; text-align: center" class="align-middle">${d.getSurname()}</td>
                        <td style="background: #cceeff; text-align: center" class="align-middle">${d.getPhoneNumber()}</td>
                        <td class="align-middle" style="text-align: center;">
                                <button class="btn button-functional" onclick="deleteDriver(${d.id})" title="Delete driver" style="background-color: red;">Delete</button>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <input type="hidden" id="user_id" name="user_id"/>
    <form method="post" action="/jjdz5-magicy/drivers-admin">
    <input type="hidden" id="id" name="id" value="0"/>
    <button type="submit" id="delete-button" hidden="hidden"></button>
    </form>

</div>

<#include "footer.ftlh">

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.13.0/umd/popper.min.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/js/mdb.min.js"></script>
</body>
</html>
