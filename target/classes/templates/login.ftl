<#import "macros/head.ftl" as h>

<@h.head font1 = "normal" font2 = "normal" font3 = "bold" header = "Авторизация">
<div class="border border-secondary rounded p-5" style="margin-left: auto; margin-right: auto; width: 400px">
    <div class="row justify-content-center">
        <div class="col-auto">
            <h6 style="font-size: 24px">Авторизация</h6>
        </div>
    </div>
    <div class="row justify-content-center mt-5">
        <div class="col">
            <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
            <div class="alert alert-danger" role="alert">
                ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
            </div>
            </#if>

            <#include "macros/alert-success.ftl">

            <form action="/login" method="post">
                <input type="hidden" name="_csrf" id="csrf" value="${_csrf.token}">
                <label class="mt-3" for="username-input">Имя пользователя</label>
                <input type="text" class="form-control" name="username" id="username-input"
                       placeholder="username" value="<#if user??>${user.username}</#if>">
                <label class="mt-3" for="password-input">Пароль</label>
                <input type="password" class="form-control" name="password" placeholder="password" id="password-input">
                <div>
                    <button class="btn btn-sm btn-primary btn-block mt-3" type="submit">Войти</button>
                </div>
                <div class="mt-3" style="width: 100%; height: 15px; border-bottom: 1px solid black; text-align: center">
                    <span style="background-color: white; padding: 0 5px">
                        или
                    </span>
                </div>
                <div class="mt-3" style="text-align: center">
                    <a href="/registration">Создать аккаунт</a>
                </div>
            </form>

            <#include "macros/alert-danger.ftl">
        </div>
    </div>
</div>
</@h.head>