<#import "macros/head.ftl" as h>

<@h.head font1="normal" font2="normal" font3="normal" header="Регистрация">
<div class="border border-secondary rounded p-5" style="margin-left: auto; margin-right: auto; width: 400px">
    <div class="row justify-content-center">
        <div class="col-auto">
            <h6 style="font-size: 24px">Регистрация</h6>
        </div>
    </div>
    <div class="row justify-content-center mt-5">
        <div class="col-auto">
            <form action="/registration" method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" id="csrf" value="${_csrf.token}">
                <div class="input-group flex-nowrap p-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="addon-wrapping-nickname" style="width: 80px">Имя</span>
                    </div>
                    <input type="text" class="form-control" name="nickname" placeholder="nickname" aria-describedby="addon-wrapping-nickname">
                </div>
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
                <div class="input-group flex-nowrap p-3">
                    <input type="password" class="form-control" name="passwordConfirm" placeholder="Повторите пароль">
                </div>
                <div class="mt-3 mb-3" style="width: 100%; height: 15px; border-bottom: 1px solid black; text-align: center">
                    <span style="background-color: white; padding: 0 5px">
                        необязательные поля
                    </span>
                </div>
                <div class="input-group flex-nowrap p-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="addon-wrapping-mail" style="width: 80px">Почта</span>
                    </div>
                    <input type="email" class="form-control" name="email" placeholder="example@email.com" aria-describedby="addon-wrapping-password">
                </div>
                <div>
                    <input type="file" name="file">
                </div>
                <div>
                    <button class="btn btn-sm btn-primary ml-3 mt-3" type="submit" style="width: 278px">Регистрация</button>
                </div>
            </form>

            <#include "macros/alert-danger.ftl">
        </div>
    </div>
</div>
</@h.head>