<!doctype html>
<html lang="en" class="full-height">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>goTogether</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/css/mdb.min.css" rel="stylesheet">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-scope" content="profile email">
    <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
    <meta name="google-signin-client_id"
          content="176776774224-o5k7gckft8lg5oc4qbf9t6ps0aet9vjc.apps.googleusercontent.com">

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
    </style>
</head>
<body>

<header>
    <script>
        function myFunction() {
              var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
       }

       function onLoad() {
      gapi.load('auth2', function() {
        gapi.auth2.init();
      });
    }
    </script>
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark indigo">
        <a class="navbar-brand" href="home"><img src="img/logo.png" width="40"
                                              hight="40" class="display-inline-block" alt=""><strong>    goTogether</strong></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="home">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
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

    <div class="view intro-2">
        <div class="mask rgba-black-light flex-center ">
            <div class="container" style="margin: auto;">
                <div class="row">
                    <div class="container white-text">
                        <div class="white-text text-center wow fadeInUp" style="font-size:30px; margin: 100px;">
                            <p style="font-size:60px;">goTogether</p>
                            car pooling made easy
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-4 text-center white-text">
                        <img class="imgCenter" src="img/dolar.png" alt="..." width="95" hight="95" class="rounded-circle">
                        <h3>save money</h3>
                    </div>
                    <div class="col-4 text-center white-text">
                        <img class="imgCenter" src="img/ecology.png" alt="..." width="95" hight="95" class="rounded-circle">
                        <h3>care about environment</h3>
                    </div>
                    <div class="col-4 text-center white-text">
                        <img class="imgCenter" src="img/friends.png" alt="..." width="95" hight="95" class="rounded-circle">
                        <h3>meet new friends</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="container white-text">
                        <div class="white-text text-center" style="font-size:25px; margin: 30px;">
                            <br>This application is dedicated to a car pooling arrangement.
                            <br>
                            <br>Looking for a nice & easy trip to work? Select 'Adverts'.
                            <br>Have a free seat in your car? Select 'Add advert'.
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>

<#include "footer.ftlh">

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.13.0/umd/popper.min.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/js/mdb.min.js"></script>
</body>
</html>
