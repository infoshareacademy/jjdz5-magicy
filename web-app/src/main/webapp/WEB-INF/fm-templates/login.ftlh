<!doctype html>
<html lang="en" class="full-height">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.10/css/mdb.min.css" rel="stylesheet">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id"
          content="176776774224-o5k7gckft8lg5oc4qbf9t6ps0aet9vjc.apps.googleusercontent.com">

    <title>Servlet OAuth example</title>
</head>
<body>
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">

<script>
      //google callback. This function will redirect to our login servlet
      function onSignIn(googleUser) {
         var profile = googleUser.getBasicProfile();
         console.log('ID: ' + profile.getId());
         console.log('Name: ' + profile.getName());
         console.log('Image URL: ' + profile.getImageUrl());
         console.log('Email: ' + profile.getEmail());
         console.log('id_token: ' + googleUser.getAuthResponse().id_token);

         //do not post all above info to the server because that is not secure.
         //just send the id_token

         var redirectUrl = 'login';

         //using jquery to post data dynamically
         var form = $('<form action="' + redirectUrl + '" method="post">' +
                          '<input type="text" name="id_token" value="' +
                           googleUser.getAuthResponse().id_token + '" />' +
                                                                '</form>');
         $('body').append(form);
         form.submit();
      }

   </script>
<div class="container">
    <div class="row" style="margin-top: 10%">
        <div class="col-4"></div>
        <div class="col-4" style="text-align: center"><img src="img/logo.png" width="100"
                                                           hight="100" class="display-inline-block" alt=""></div>
        <div class="col-4"></div>
        </div>

    <div class="row">
        <div class="col-4"></div>
        <div class="col-4" style="text-align: center"><h1>goTogether</h1></div>
        <div class="col-4"></div>
    </div>
        <div class="row">
            <div class="col-5"></div>
            <div class="col-2 col-centered">
                <div style="margin-right:20%"></div><div syle="margin-top:10px" class="g-signin2" data-onsuccess="onSignIn"></div>
            </div>
            <div class="col-5"></div>
        </div>
    </div>
</div>



</div>
</body>
</html>