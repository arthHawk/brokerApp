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
<table>
    <tr>
        <th>ID</th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Действия</th>

    </tr>
    <#list clientsAttr as client>
        <tr>
            <td>${client.id}</td>
            <td>${client.surname}</td>
            <td>${client.name}</td>
            <td>
                <form method="post" action="/adminClients/${client.id}/adminDelete">
                    <input type="submit" value="Удалить">
                </form>
                <a href="/adminClients/${client.id}">Перейти</a>
            </td>
        </tr>
    </#list>
</table>

<form method="post" action="/adminAddClient">
    <label>Введите логин для авторизации на сайте:
        <input name="login" placeholder="Введите логин: ">
    </label>
    <label>Введите пароль для авторизации на сайте:
        <input type="password" name="password" placeholder="Введите пароль: ">
    </label>
    <label>Введите фамилию:
        <input name="surname" placeholder="Введите фамилию: ">
    </label>
    <label>Введите имя:
        <input name="name" placeholder="Введите имя: ">
    </label>
    <input type="submit" value="Добавить">

</form>
</body>
</html>