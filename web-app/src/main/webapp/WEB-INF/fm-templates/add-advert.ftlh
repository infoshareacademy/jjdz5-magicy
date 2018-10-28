<!doctype html>
<html lang="en" class="full-height">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>goTogether</title>

    <link rel="stylesheet" href="css/overall.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/css/mdb.min.css" rel="stylesheet">
    <script src="https://unpkg.com/sweetalert2@7.17.0/dist/sweetalert2.all.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="js/advertCtrl.js" type="text/javascript"></script>

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
                    <li class="nav-item active">
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
      <div class="row">
          <div class="mt-5 mb-5">
              <h3>Add new advert</h3>
          </div>
      </div>
      <form method="post" action="/jjdz5-magicy/add-advert">
          <div class="row border-bottom"><div class="col-12"><h3 class="text-muted">From</h3></div></div>
          <div class="row margin">
              <div class="col-5 form-group">
                  <label for="startCity">City:</label>
                  <input id="startCity"
                         required
                         type="text"
                         name="startCity"
                         placeholder="Enter departure city"
                         class="form-control" />
            </div>

            <div class="col-5 form-group">
                <label for="startStreet">Street:</label>
                <input id="startStreet"
                       type="text"
                       required
                       name="startStreet"
                       placeholder="Enter departure street"
                       class="form-control" />
            </div>
            <div class="col-2 form-group">
                <label for="startTime">At time (HH:mm):</label>
                <input id="startTime"
                       type="time"
                       required
                       name="startTime"
                       placeholder="Enter departure time"
                       class="form-control" />
            </div></div>

        <div class="row border-bottom"><div class="col-12 center"><h3 class="text-muted">To</h3></div></div>
        <div class="row margin">
            <div class="col-5 form-group">
                <label for="endCity">City:</label>
                <input id="endCity"
                       type="text"
                       required
                       name="endCity"
                       placeholder="Enter arrival city"
                       class="form-control" />
            </div>

            <div class="col-5 form-group">
                <label for="endStreet">Street:</label>
                <input id="endStreet"
                       type="text"
                       required
                       name="endStreet"
                       placeholder="Enter arrival street"
                       class="form-control" />
            </div>
            <div class="col-2 form-group">
                <label for="endTime">At time (HH:mm):</label>
                <input id="endTime"
                       type="time"
                       required
                       name="endTime"
                       placeholder="Enter arrival time"
                       class="form-control" />
            </div></div>

        <div class="row border-bottom"><div class="col-12 center"><h3 class="text-muted">Via</h3></div></div>
        <div class="row margin">
            <div class="col-5 form-group">
                <label for="pickUpCity">City:</label>
                <input id="pickUpCity"
                       type="text"
                       name="pickUpCity"
                       placeholder="Enter city where you stop"
                       class="form-control" />
            </div>

            <div class="col-5 form-group">
                <label for="pickUpStreet">Street:</label>
                <input id="pickUpStreet"
                       type="text"
                       name="pickUpStreet"
                       placeholder="Enter street where you stop"
                       class="form-control" />
            </div>
            <div class="col-2 form-group">
                <label for="pickUpTime">At time (HH:mm):</label>
                <input id="pickUpTime"
                       type="time"
                       name="pickUpTime"
                       placeholder="Enter time of your stop"
                       class="form-control" />
            </div></div>
        <div class="row">
            <div class="col-12 form-group text-muted">
                <h3><label for="date">Date</label></h3>
                <input id="date"
                       required
                       type="date"
                       name="date"
                       class="form-control" />
            </div>
        </div>
          <div class="row margin">
              <div class="col-3 imgCenter">
                  <button type="submit" id="add_button" class="btn btn-primary btn-block">Add</button>
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