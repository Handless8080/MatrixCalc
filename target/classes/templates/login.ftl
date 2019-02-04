<#import "macros/head.ftl" as h>

<@h.head font1="normal" font2="normal" header="Логин" flag="false">
<span>Логин</span>
<a href="/registration">Создать аккаунт</a>
<form action="/login" method="post">
    <div>
        <input type="text" name="username" placeholder="Логин">
    </div>
    <div>
        <input type="password" name="password" placeholder="Пароль">
    </div>
    <div>
        <button type="submit">Войти</button>
    </div>
    <input type="hidden" name="_csrf" id="csrf" value="${_csrf.token}">
</form>
<div>

</div>
</@h.head>