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
        .margin{
            margin-bottom:40px;
            margin-top:20px;
        }
    </style>
</head>
<body>
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
                <li class="nav-item">
                    <a class="nav-link" href="adverts">Adverts</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="add-advert">Add advert</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="drivers">Drivers</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="add-driver">Add driver</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="row text-center margin"><h1 class="text-center"></h1></div>
    <form method="post" action="/jjdz5-magicy/add-driver">
        <div class="row border-bottom"><div class="col-12"><h3 class="text-muted">New Driver</h3></div></div>
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
                <button type="submit" class="btn btn-primary btn-block">Add</button>
            </div>
        </div>
    </form>
</div>

<#include "footer.ftlh">

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.13.0/umd/popper.min.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/js/mdb.min.js"></script>
</body>
</html>