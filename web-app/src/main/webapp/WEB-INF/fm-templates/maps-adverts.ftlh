<!doctype html>
<html lang="en" class="full-height">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>goTogether</title>
    <link rel="stylesheet" href="css/overall.css">
    <link rel="stylesheet" href="css/maps-adverts.css">
    <link rel="stylesheet" href="css/filterables.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/css/mdb.min.css" rel="stylesheet">
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <#include "google-log.ftlh">
    <style>
        .card {
            padding: 5px !important;
        }
    </style>
</head>
<body>
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
        <div class="col-12" style="padding-left: 0">
            <button class="btn button" disabled>${language.getString("advertsList")}</button>
        </div>
    </div>

    <div class="row mt-3 mb-5">

        <div class="card">
            <div class="panel panel-primary filterable">
                <table class="table table-bordered table-sm">
                    <thead>
                        <tr class="filters">
                            <th class="align-middle"><input style="text-align: center" type="text" class="form-control" placeholder="Departure address" disabled></th>
                            <th class="align-middle"><input style="text-align: center" type="text" class="form-control" placeholder="Departure time" disabled></th>
                            <th class="align-middle"><input style="text-align: center" type="text" class="form-control" placeholder="Destination address" disabled></th>
                            <th class="align-middle"><input style="text-align: center; min-width: 90px" type="text" class="form-control" placeholder="Destination time" disabled></th>
                            <th class="align-middle"><input style="text-align: center" type="text" class="form-control" placeholder="Date" disabled></th>
                            <th class="align-middle"><input style="text-align: center" type="text" class="form-control" placeholder="Driver & Car" disabled></th>
                            <th class="text-center"><button class="btn btn-filter button-functional" style="background-color: #2bbbad!important">
                                Filter
                            </button></th>
                        </tr>

                    </thead>
                    <tbody>
                    <#list ADVERTS as advert>
                    <#setting time_format="HH:mm">
                         <tr>
                             <td style="background: #cceeff; text-align: center" class="align-middle">${advert.startAddress.formattedAddress}</td>
                             <td style="background: #cceeff; text-align: center" class="align-middle">${advert.startTime?time}</td>
                             <td style="background: #fff; text-align: center" class="align-middle">${advert.endAddress.formattedAddress}</td>
                             <td style="background: #fff; text-align: center" class="align-middle">${advert.endTime?time}</td>
                             <td style="background: #cceeff; text-align: center" class="align-middle">${advert.date?string}</td>
                             <#assign car = advert.driver.cars?first>
                             <td style="background: #cceeff; text-align: center" class="align-middle">${advert.driver.name} ${advert.driver.surname}<br>${car.carBrand} ${car.carModel}</td>
                             <td class="align-middle">
                                 <form action="/jjdz5-magicy/maps-adverts" method="post">
                                    <button type="submit" class="btn button-functional" name="advertId" value="${advert.id}">Details</button>
                                 </form>
                             </td>
                         </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<#include "footer.ftlh">

<script src="js/preloader.js"></script>
<script src="js/filterable-table.js"></script>
</body>
</html>
