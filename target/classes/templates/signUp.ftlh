
<!doctype html>
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Регистрация нового пользователя</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<h1>Введите учетные данные, необходимые для регистрации:</h1>

<form method="post" action="/signUp">

    <@spring.bind path="clientForm"/>
    <p class="error">${spring.status.errorMessage}</p>

    <label>Введите логин для авторизации на сайте:
        <#--        <input name="login" placeholder="Введите логин: ">-->
        <@spring.formInput "clientForm.login"/>
        <@spring.showErrors "<br>" "error"/>
        <#if !isLoginUnique>
            <p class="error">Такой логин уже существует. Введите уникальный логин</p>
        </#if>
    </label>
    <br>

    <label>Введите пароль для авторизации на сайте:
<#--        <input type="password" name="password" placeholder="Введите пароль: ">-->
        <@spring.formPasswordInput "clientForm.password"/>
        <@spring.showErrors "<br>" "error"/>
    </label>
    <br>

    <label>Введите фамилию:
<#--        <input name="surname" placeholder="Введите фамилию: ">-->
        <@spring.formInput "clientForm.surname"/>
        <@spring.showErrors "<br>" "error"/>
    </label>
    <br>

    <label>Введите имя:
<#--        <input name="name" placeholder="Введите имя: ">-->
        <@spring.formInput "clientForm.name"/>
        <@spring.showErrors "<br>" "error"/>
    </label>
    <br>
    <input type="submit" value="Добавить">

</form>
</body>
</html>