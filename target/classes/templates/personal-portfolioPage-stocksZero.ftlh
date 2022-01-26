<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${client.name}</title>
</head>
<body>
<h3>Имя - ${client.name} </h3>
<h3>Фамилия - ${client.surname} </h3>
<h3>Наличные на счету - ${client.cash} </h3>

<br>
<form action="/portfolio/${client.id}/cash/add">
    <label>Пополнение баланса: </label>
    <input type="submit" value="Пополнить баланс">
</form>
<br>

<form action="/portfolio/${client.id}/cash/withdrawal">
    <label>Вывод средств: </label>
    <input type="submit" value="Вывести средства">
</form>
<br>

<form action="/portfolio/${client.id}/edit">
    <label>Редактирование профиля: </label>
    <input type="submit" value="Редактировать профиль">
</form>
<br>

<form action="/portfolio/${client.id}/stocks/buy">
    <label>Покупка акций: </label>
    <input type="submit" value="Купить акции">
</form>

<h3>В вашем портфеле пока ценных бумаг!</h3>

<br/>
<a href="/portfolio_logout" />Выход из профиля</a>

</body>
</html>