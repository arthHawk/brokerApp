<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Вывод средств</title>
</head>
<body>
<h3>${client.name}</h3>
<br>

<form method="post" action="/portfolio/${client.id}/cash/withdrawal">
    <input name="withdrawalValue" placeholder="Введите сумму средств для вывода">
    <input type="submit" value="Вывести средства">
</form>
<br>

</body>
</html>