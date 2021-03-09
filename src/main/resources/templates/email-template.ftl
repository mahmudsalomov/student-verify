<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body class="">

<div class="">
    <h2 class="col-12">${firstname}</h2>
    <h2 class="col-12">${lastname}</h2>
    <h2 class="col-12">${email}</h2>
    <button style="background-color: green; color: white" class="button">
        <a href="${url}/verify?id=${id}&code=${code}&cancel=false" class="">Confrim</a>
    </button>
    <button style="background-color: red; color: white" class="button">
        <a href="${url}/verify?id=${id}&code=${code}&cancel=true" class="">Cancel</a>
    </button>
</div>

</body>
</html>