<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Акции</title>
</head>
<h1>Portfolios текущего пользователя</h1>
<body>
    <table>
        <tr>
            <th>issuer</th>
            <th>ticker</th>
            <th>quote</th>
            <th>count</th>
            <th>total</th>
        </tr>
        <#list portfolio as pf>
            <tr>
                <td>${pf.issuer}</td>
                <td>${pf.ticker}</td>
                <td>${pf.quote}</td>
                <td>${pf.count}</td>
                <td>${pf.quote*pf.count}</td>
            </tr>
        </#list>
    </table>
</body>
</html>