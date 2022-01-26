<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<h3>Выберите акции для покупки: </h3>
<body>
<table>
    <tr>
        <th>Эмитент</th>
        <th>Тикер</th>
        <th>Цена акции</th>
        <th>Действия</th>

    </tr>
    <#list stocks as stock>
        <tr>
            <td>${stock.issuer}</td>
            <td>${stock.ticker}</td>
            <td>${stock.quote}</td>
            <td>
                <form action="/portfolio/${client.id}/stocks/buy/${stock.id}">
                    <input type="submit" value="Купить">
                </form>
            </td>
        </tr>
    </#list>
</table>

</body>
</html>