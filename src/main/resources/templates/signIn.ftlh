<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Вход в личный кабинет</title>
</head>
<body>
<h1>Введите логин и пароль для входа в личный кабинет</h1>
<form method="post" action="/signIn">
    <label>Введите логин для авторизации на сайте:
        <input name="login" placeholder="Введите логин: ">
    </label>
    <br>

    <label>Введите пароль для авторизации на сайте:
        <input type="password" name="password" placeholder="Введите пароль: ">
    </label>
    <input type="submit" value="Войти">
</form>
</body>
</html>