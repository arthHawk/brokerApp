<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h3>Пользователь: ${client.name} ${client.surname}</h3>
<h3>Количество средств для покупки: ${client.cash}</h3>
<h3>Тикер: ${stock.ticker}</h3>
<h3>Цена акции: ${stock.quote}</h3>
<br>

<label>Введите количество акций для покупки:</label>
<br>

<form method="post" action="/portfolio/${client.id}/stocks/buy/${stock.id}">
    <input name="stocksCount" placeholder="Введите количество акций для покупки">
    <input type="submit" value="Подтвердить покупку">
</form>
<br>

</body>
</html>