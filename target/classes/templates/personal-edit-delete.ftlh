<!doctype html>
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Пополнение баланса</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<h3>Текущие данные:</h3>
<h3>Логин: ${client.login}</h3>
<h3>Имя: ${client.name}</h3>
<h3>Фамилия: ${client.surname}</h3>
<br>

<#--<form method="post" action="portfolio/${client.id}/edit/update">-->
<form method="post" action="edit/update">
<#--<form method="post" action="update">-->

<#--    <@spring.bind path="clientForm"/>-->
<#--    <p class="error">${spring.status.errorMessage}</p>-->

    <label>
        Логин: <input name="login" placeholder="${client.login}">
        <#if !isLoginUnique>
            <p class="error">Такой логин уже существует. Введите уникальный логин</p>
        </#if>
<#--        Логин:-->
<#--        <@spring.formInput "clientForm.login" "placeholder=${client.login}"/>-->
<#--        <@spring.showErrors "<br>" "error"/>-->
    </label>
    <br>
    <br>
    <label>
        Пароль: <input type="password" name="password">
    </label>
    <br>
    <br>
    <label>
        Имя: <input name="name" placeholder="${client.name}">
    </label>
    <br>
    <br>
    <label>
        Фамилия: <input name="surname" placeholder="${client.surname}">
    </label>
    <br>
    <input type="submit" value="Сохранить изменения в профиле">
</form>
<br>

<#--<form method="post" action="portfolio/${client.id}/edit/delete">-->
<form method="post" action="edit/delete">
    <input type="submit" value="Удалить аккаунт">
</form>

</body>
</html>