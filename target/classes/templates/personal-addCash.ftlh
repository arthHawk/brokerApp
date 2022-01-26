<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Пополнение баланса</title>
</head>
<body>
<h3>${client.name}</h3>
<br>
<form method="post" action="/portfolio/${client.id}/cash/add">
    <input name="addValue" placeholder="Введите сумму для пополнения баланса">
    <input type="submit" value="Пополнить баланс">
</form>
<br>

</body>
</html>