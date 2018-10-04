<!doctype html>
<html lang="en" class="full-height">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>goTogether</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/css/mdb.min.css" rel="stylesheet">

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
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark indigo">
        <a class="navbar-brand" href="#"><img src="logo.png" width="30"
                                              hight="30" class="display-inline-block" alt=""><strong>goTogether</strong></a>
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
                    <a class="nav-link" href="#">Adverts</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="add-driver">Add driver</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="add-advert">Add advert</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Driver's rating</a>
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
                            <h2>GoTogether</h2>
                            application is used to arrange a common car trip in the same direction to work.
                            A person who runs a car every day and has, for example,
                            three vacancies, can take someone else with him.
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
                        <h3>care about ecology</h3>
                    </div>
                    <div class="col-4 text-center white-text">
                        <img class="imgCenter" src="img/friends.png" alt="..." width="95" hight="95" class="rounded-circle">
                        <h3>meet new friends</h3>
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
