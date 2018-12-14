<nav class="navbar fixed-top navbar-expand-lg navbar-dark indigo">
    <a class="navbar-brand" href="home"><img src="img/logo.png" width="40"
                                             hight="40" class="display-inline-block" alt=""><strong>    goTogether</strong></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li id="1nav" class="nav-item">
                <a class="nav-link" href="home">Home <span class="sr-only">(current)</span></a>
            </li>
            <li id="2nav" class="nav-item">
                <a class="nav-link" href="maps-adverts">Adverts</a>
            </li>
            <#if user.isDriver() == true>
            <li id="3nav" class="nav-item">
                <a class="nav-link" href="add-maps-advert">Add advert</a>
            </li>
        </#if>
        <li id="4nav" class="nav-item">
            <a class="nav-link" href="drivers">Drivers</a>
        </li>
        <#if user.isDriver() == false>
        <li  id="5nav" class="nav-item">
            <a class="nav-link" href="add-driver">Add driver</a>
        </li>
        </#if>
<li id="7nav" class="nav-item">
    <a class="nav-link" href="about-us">About us</a>
</li>
<#if user.isAdmin() == true>
<li id="8nav" class="nav-item">
    <a class="nav-link" href="admin-panel">Admin Panel</a>
</li>
</#if>
</ul>
</div>
</nav>