<!doctype html>
<html lang="en" class="full-height">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>goTogether</title>

    <link rel="stylesheet" href="css/overall.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="sytlesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.0/bootstrap-table.min.css">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/css/mdb.min.css" rel="stylesheet">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
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

    document.getElementById("2nav").classList.add('active');

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
</script>

<div class="container">
    <#include "menu.ftlh">
<#--The columns titles are merged with the filters inputs thanks to the placeholders attributes-->
    <div class="row">
        <div class="mt-5">
            <h3>Adverts</h3>
        </div>
        <div class="panel panel-primary filterable">
            <div class="panel-heading">
                <div class="pull-right">
                    <button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span> Filter</button>
                </div>
            </div>
            <table class="table">

                <thead>
                    <tr class="filters">
                        <th><input type="text" class="form-control" placeholder="Advert" disabled></th>
                        <th><input type="text" class="form-control" placeholder="Date" disabled></th>
                        <th><input type="text" class="form-control" placeholder="From" disabled></th>
                        <th><input type="text" class="form-control" placeholder="Time" disabled></th>
                        <th><input type="text" class="form-control" placeholder="Via" disabled></th>
                        <th><input type="text" class="form-control" placeholder="Time" disabled></th>
                        <th><input type="text" class="form-control" placeholder="To" disabled></th>
                        <th><input type="text" class="form-control" placeholder="Time" disabled></th>
                        <th><input type="text" class="form-control" placeholder="Driver" disabled></th>
                    </tr>
                </thead>
	<tbody>
	<#list adverts as a>
    <#if a.getPromo() == true>
        <tr style="background: #ffffcc">
                <td>${a.id}</td>
                <td>${a.getRoute().getDate()?string("dd-MM-yyyy")}</td>
                <td style="background: #ffcc66">${a.getRoute().getStartCity()} <br>${a.getRoute().getStartStreet()}</td>
                <td style="background: #ffcc66">${a.getRoute().getStartTime()}</td>
                <td>${a.getRoute().getPickUpCity()} <br>${a.getRoute().getPickUpStreet()}</td>
                <td>${a.getRoute().getPickUpTime()}</td>
                <td style="background: #ffcc66">${a.getRoute().getEndCity()} <br>${a.getRoute().getEndStreet()}</td>
                <td style="background: #ffcc66">${a.getRoute().getEndTime()}</td>
                <td>${a.getDriver().getName()} ${a.getDriver().getSurname()} <br>${a.getDriver().getPhone()}</td>
        </tr>
    </#if>
    </#list>
    <#list adverts as a>
        <#if a.getPromo() == false>
         <tr>
             <td>${a.id}</td>
             <td>${a.getRoute().getDate()?string("dd-MM-yyyy")}</td>
             <td style="background: #cceeff">${a.getRoute().getStartCity()} <br>${a.getRoute().getStartStreet()}</td>
             <td style="background: #cceeff">${a.getRoute().getStartTime()}</td>
             <td>${a.getRoute().getPickUpCity()} <br>${a.getRoute().getPickUpStreet()}</td>
             <td>${a.getRoute().getPickUpTime()}</td>
             <td style="background: #cceeff">${a.getRoute().getEndCity()} <br>${a.getRoute().getEndStreet()}</td>
             <td style="background: #cceeff">${a.getRoute().getEndTime()}</td>
             <td>${a.getDriver().getName()} ${a.getDriver().getSurname()} <br>${a.getDriver().getPhone()}</td>
         </tr>
        </#if>
    </#list>
	</tbody>
            </table>
        </div>
    </div>
</div>

<#include "footer.ftlh">

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.13.0/umd/popper.min.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/js/mdb.min.js"></script>
</body>
</html>
