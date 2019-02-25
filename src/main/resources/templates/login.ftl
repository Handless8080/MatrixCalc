<#import "macros/head.ftl" as h>

<@h.head font1="normal" font2="normal" font3="bold" header="Авторизация">
<div class="border border-secondary rounded p-5" style="margin-left: auto; margin-right: auto; width: 400px">
    <div class="row justify-content-center">
        <div class="col-auto">
            <h6 style="font-size: 24px">Авторизация</h6>
        </div>
    </div>
    <div class="row justify-content-center mt-5">
        <div class="col-auto">
            <form action="/login" method="post">
                <input type="hidden" name="_csrf" id="csrf" value="${_csrf.token}">
                <div class="input-group flex-nowrap p-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="addon-wrapping-login" style="width: 80px">Логин</span>
                    </div>
                    <input type="text" class="form-control" name="username" placeholder="username" aria-describedby="addon-wrapping-login">
                </div>
                <div class="input-group flex-nowrap p-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="addon-wrapping-password" style="width: 80px">Пароль</span>
                    </div>
                    <input type="password" class="form-control" name="password" placeholder="password" aria-describedby="addon-wrapping-password">
                </div>
                <div>
                    <button class="btn btn-sm btn-primary ml-3 mt-3" type="submit" style="width: 278px">Войти</button>
                </div>
                <div class="mt-3" style="width: 100%; height: 15px; border-bottom: 1px solid black; text-align: center">
                    <span style="background-color: #F3F5F6; padding: 0 5px">
                        или
                    </span>
                </div>
                <div class="mt-3" style="text-align: center">
                    <a href="/registration">Создать аккаунт</a>
                </div>
            </form>

            <#if message??>
            <div class="alert alert-danger" role="alert">
                A simple danger alert—check it out!
            </div>
            </#if>
        </div>
    </div>
</div>
</@h.head>