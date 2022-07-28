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
    <br>
    <form action="/admin-clients/${client.id}/admin-update">
        <label>
            Имя: <input name="name" placeholder="${client.name}">
        </label>
        <label>
            Фамилия: <input name="Surname" placeholder="${client.surname}">
        </label>
        <input type="submit" value="Сохранить">
    </form>
</body>
</html>