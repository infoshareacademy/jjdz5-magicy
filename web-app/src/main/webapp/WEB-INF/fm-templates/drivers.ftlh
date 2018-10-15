<!doctype html>
<html lang="en" class="full-height" xmlns="http://www.w3.org/1999/html">
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
    <script src="https://unpkg.com/sweetalert2@7.17.0/dist/sweetalert2.all.js"></script>

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

function myAlertFunction(id, name, surname) {
var inputOptions = new Promise(function(resolve) {
    setTimeout(function() {
      resolve({
        '1': '1',
        '2': '2',
        '3': '3',
        '4': '4',
        '5': '5',
      })
    }, 1)
  })
  swal({
      title: 'Your rating for '+name+' '+surname,
      input: 'radio',
       confirmButtonText: "Add",
       showCloseButton: true,
      inputOptions: inputOptions,
      inputValidator: (value) => {
          return !value && 'You need to choose something!'
        }
    }).then((result) => {
  if (result.value) {
    $('#rating').val(result.value);
    $('#id').val(id);
    $('#sub').click();
  }
  else{
  }
})
}
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
                    <a class="nav-link" href="home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="adverts">Adverts</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="add-advert">Add advert</a>
                </li>
                <li class="nav-item active">
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
            <h3>Drivers</h3>
        </div>
    </div>
    <form method="post" action="/jjdz5-magicy/drivers">
    <div class="row">
        <div class="panel panel-primary filterable" style="width:100%">
            <table class="table" style="width: 100%; text-align: center">
                <thead>
                    <tr class="filters">
                        <th style="width:10%">Number</th>
                        <th style="width:50%">Driver</th>
                        <th style="width:30%">Rating</th>
                        <th style="width:20%"></th>
                    </tr>

                </thead>
	<tbody>
	<#list drivers as d>
		<tr>
            <td style="width:10%; vertical-align: middle">${d.getId()}</td>
            <td style="width:50%; vertical-align: middle">${d.getName()} ${d.getSurname()} ${d.getPhone()} ${d.getCity()}</td>
			<td style="width:30%; vertical-align: middle">Rating: ${d.getRating().getAverage()}/5,0  <br/> ${d.getRating().getPersons()} reviews</td>
            <td style="width:20%"><button onclick="myAlertFunction(${d.getId()}, '${d.getName()}', '${d.getSurname()}')" id="add-button" type="button" class="btn btn-info" style="width:100%">Add rating</button></td>
		</tr>
		</#list>
	</tbody>
            </table>
        </div>
    </div>
    <input type="hidden" id="id" name="id" value="0"/>
    <input type="hidden" id="rating" name="rating" value="0"/>
        <button type="submit" id="sub" hidden="hidden"></button>
    </form>
</div>

<#include "footer.ftlh">

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.13.0/umd/popper.min.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/js/mdb.min.js"></script>
</body>
</html>

