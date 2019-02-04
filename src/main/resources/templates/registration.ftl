<#import "macros/head.ftl" as h>

<@h.head font1="normal" font2="normal" header="Регистрация" flag="false">
<span>Авторизация</span>
<form action="/registration" method="post">
    <input type="hidden" name="_csrf" id="csrf" value="${_csrf.token}">
    <div>
        <input type="text" name="username" placeholder="Никнейм">
    </div>
    <div>
        <input type="text" name="login" placeholder="Логин">
    </div>
    <div>
        <input type="password" name="password" placeholder="Пароль">
    </div>
    <div>
        <button type="submit">Регистрация</button>
    </div>
</form>
</@h.head>