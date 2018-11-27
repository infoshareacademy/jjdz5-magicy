<!doctype html>
<html lang="en" class="full-height">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>goTogether</title>

    <script src="js/jquery-3.1.1.min.js"></script>
    <#--<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>-->
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
                    <li class="nav-item active">
                        <a class="nav-link" href="adverts">Adverts</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="add-advert">Add advert</a>
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
<#--The columns titles are merged with the filters inputs thanks to the placeholders attributes-->
    <div class="row">
        <div class="mt-5">
            <h3>Adverts</h3>
        </div>
        <div class="panel panel-primary filterable">
            <table class="table table-bordered table-sm">
                <thead>
                    <tr class="filters">
                        <th class="align-middle"><input style="text-align: center" type="text" class="form-control" placeholder="Departure address" disabled></th>
                        <th class="align-middle"><input style="text-align: center" type="text" class="form-control" placeholder="Departure time" disabled></th>
                        <th class="align-middle"><input style="text-align: center" type="text" class="form-control" placeholder="Destination address" disabled></th>
                        <th class="align-middle"><input style="text-align: center" type="text" class="form-control" placeholder="Destination time" disabled></th>
                        <th class="align-middle"><input style="text-align: center" type="text" class="form-control" placeholder="Date" disabled></th>
                        <th class="align-middle"><input style="text-align: center" type="text" class="form-control" placeholder="Driver & Car" disabled></th>
                        <th class="text-center"><button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span>Filter</button></th>
                    </tr>

                </thead>
                <tbody>
                <#list ADVERTS as advert>
                     <tr>
                         <#if advert.startAddress.info?length &gt; 0>
                             <td style="background: #cceeff; text-align: center" class="align-middle">${advert.startAddress.info}<hr style="margin: 5px">${advert.startAddress.city}<br>${advert.startAddress.streetName} ${advert.startAddress.streetNumber}</td>
                         <#else>
                             <td style="background: #cceeff; text-align: center" class="align-middle">${advert.startAddress.city}<br>${advert.startAddress.streetName} ${advert.startAddress.streetNumber}</td>
                         </#if>
                         <td style="background: #cceeff; text-align: center" class="align-middle">${advert.startTime?string}</td>
                         <#if advert.endAddress.info?length &gt; 0>
                            <td style="background: #fff; text-align: center" class="align-middle">${advert.endAddress.info}<hr style="margin: 5px">${advert.endAddress.city}<br>${advert.endAddress.streetName} ${advert.endAddress.streetNumber}</td>
                         <#else>
                            <td style="background: #fff; text-align: center" class="align-middle">${advert.endAddress.city}<br>${advert.endAddress.streetName} ${advert.endAddress.streetNumber}</td>
                         </#if>
                         <td style="background: #fff; text-align: center" class="align-middle">${advert.endTime?string}</td>
                         <td style="background: #cceeff; text-align: center" class="align-middle">${advert.date?string}</td>
                         <#assign car = advert.driver.cars?first>
                         <td style="background: #cceeff; text-align: center" class="align-middle">${advert.driver.name} ${advert.driver.surname}<br>${car.carBrand} ${car.carModel}</td>
                         <td class="align-middle">
                             <form action="/jjdz5-magicy/maps-adverts" method="post">
                                <button type="submit" class="btn btn-primary" name="advertId" value="${advert.id}">Details</button>
                             </form>
                         </td>
                     </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>

<#include "footer.ftlh">


<#--<script>-->
    <#--function buildDetailsModal(startAddressId, endAddressId) {-->
        <#--var startAddress = getAddressJson(startAddressId);-->
        <#--var endAddress = getAddressJson(endAddressId);-->
        <#--var paragraph = document.createElement('p');-->
        <#--var sFormat = startAddress["status"];-->
        <#--var eFormat = endAddress["status"];-->
        <#--paragraph.innerText = sFormat + " || " + eFormat;-->

        <#--var div = document.getElementById("modalBuilder");-->
        <#--div.appendChild(paragraph);-->

    <#--}-->

    <#--function getAddressJson(addressId) {-->
        <#--return $.getJSON('https://maps.googleapis.com/maps/api/place/details/json?key=${apiKey}&placeid=' + addressId);-->
    <#--}-->
<#--</script>-->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/js/mdb.min.js"></script>
</body>
</html>
