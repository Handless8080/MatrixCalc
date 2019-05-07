<#import "macros/head.ftl" as h>

<@h.head font1 = "normal" font2 = "normal" font3 = "bold" header = "Авторизация">
<div class="border border-secondary rounded p-5 mlr-auto" style="width: 400px">
    <div class="row justify-content-center">
        <div class="col-auto">
            <h6>Авторизация</h6>
        </div>
    </div>
    <div class="row justify-content-center mt-5">
        <div class="col">
            <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
            <div class="alert alert-danger" role="alert">
                Логин или пароль введены неверно
            </div>
            </#if>

            <#include "macros/alert-success.ftl">
            <#include "macros/alert-danger.ftl">

            <form action="/login" method="post">
                <input type="hidden" name="_csrf" id="csrf" value="${_csrf.token}">
                <#include "macros/login-form.ftl">
                <div>
                    <button class="btn btn-sm btn-primary btn-block mt-3" type="submit" id="btn" disabled>Войти</button>
                </div>
                <div class="mt-3 h-line">
                    <span class="ol-text">
                        или
                    </span>
                </div>
                <div class="mt-3" style="text-align: center">
                    <a href="/registration">Создать аккаунт</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="../js/user-valid.js"></script>
<script src="../js/validation.js"></script>
</@h.head>