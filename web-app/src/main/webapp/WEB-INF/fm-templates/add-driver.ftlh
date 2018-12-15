<!doctype html>
<html lang="en" class="full-height">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>goTogether</title>

    <link rel="stylesheet" href="css/overall.css">
    <script src="js/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/css/mdb.min.css" rel="stylesheet">
    <script src="https://unpkg.com/sweetalert2@7.17.0/dist/sweetalert2.all.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="js/driverCtrl.js" type="text/javascript"></script>

    <#include "google-log.ftlh">
    <style>
        .view {
            background-repeat: no-repeat;
            background-position: center;
            background-image: url("img/background.jpg");
            background-size: cover;
        }
        .imgCenter {
            display: block;
            margin: auto;
        }
        .margin{
            margin-bottom:40px;
            margin-top:20px;
        }
    </style>
</head>
<body>
<script>
    $(document).ready(function(){
    document.getElementById("5nav").classList.add('active');
    });
</script>
<div class="container">
    <#include "menu.ftlh">
    <div class="row">
        <div class="mt-5 mb-5">
            <h3>Add new driver</h3>
        </div>
    </div>
        <div class="row margin">
            <div class="col-5 form-group">
                <label for="name">Name:</label>
                <input id="name"
                       required
                       type="text"
                       name="name"
                       placeholder="Enter your name"
                       class="form-control" />
            </div>

            <div class="col-5 form-group">
                <label for="surname">Surname:</label>
                <input id="surname"
                       type="text"
                       required
                       name="surname"
                       placeholder="Enter your surname"
                       class="form-control" />
            </div>
            </div>

        <div class="row margin">
            <div class="col-5 form-group">
                <label for="city">City:</label>
                <input id="city"
                       type="text"
                       required
                       name="city"
                       placeholder="Enter your city"
                       class="form-control" />
            </div>

            <div class="col-5 form-group">
                <label for="district">District:</label>
                <input id="district"
                       type="text"
                       required
                       name="district"
                       placeholder="Enter your district"
                       class="form-control" />
            </div>
            </div>

        <div class="row">
            <div class="col-5 form-group">
                <label for="phone">Your phone number (xxx xxx xxx)</label>
                <input id="phone"
                       required
                       type="phone"
                       name="phone"
                       placeholder="xxx xxx xxx"
                       class="form-control" />
            </div>
        </div>
        <div class="row margin">
            <div class="col-3 imgCenter">
                <button type="submit" class="btn btn-primary btn-block" onclick="addDriver()">Add</button>
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