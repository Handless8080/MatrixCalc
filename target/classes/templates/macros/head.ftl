<#macro head>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MatrixCalculator</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: seagreen; margin-bottom: 5px">
    <a class="navbar-brand" style="font-size: 22pt; font-weight: bold"><span style="color: darkviolet">Matrix</span><span style="color: black">Calculator</span></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbar">
        <div class="navbar-nav">
            <a class="nav-link nav-item" href="#" style="color: white; font-size: 14pt; font-weight: bold">Калькулятор</a>
            <a class="nav-link nav-item" href="#" style="color: white; font-size: 14pt">Форум</a>
        </div>
        <div class="navbar-nav ml-auto">
            <a class="nav-link nav-item" href="#" style="color: lightblue; font-size: 12pt">Авторизация</a>
        </div>
    </div>
</nav>
<div style="margin-left: 5px">
    <#nested>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
</#macro>