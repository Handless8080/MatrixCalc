<#import "macros/head.ftl" as h>

<@h.head font1 = "normal" font2 = "normal" font3 = "normal" header = "Регистрация">
<div class="border border-secondary rounded p-5" style="margin-left: auto; margin-right: auto; width: 400px">
    <div class="row justify-content-center">
        <div class="col-auto">
            <h6 style="font-size: 24px">Регистрация</h6>
        </div>
    </div>
    <div class="row justify-content-center mt-5">
        <div class="col">
            <form action="/registration" method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" id="csrf" value="${_csrf.token}">
                <label class="mt-3" for="nickname-input">Никнейм</label>
                <input type="text" class="form-control ${(nicknameError??)?string('is-invalid','')}" name="nickname" id="nickname-input"
                       placeholder="nickname" value="<#if user??>${user.nickname}</#if>">
                <#if nicknameError??>
                <div class="invalid-feedback">
                    ${nicknameError}
                </div>
                </#if>
                <label class="mt-3" for="username-input">Имя пользователя</label>
                <input type="text" class="form-control ${(nicknameError??)?string('is-invalid','')}" name="username" id="username-input"
                       placeholder="username" value="<#if user??>${user.username}</#if>">
                <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
                </#if>
                <label class="mt-3" for="password-input">Пароль</label>
                <input type="password" class="form-control ${(passwordError??)?string('is-invalid','')}" name="password" id="password-input"
                       placeholder="password">
                <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
                </#if>
                <label class="mt-3" for="password2-input">Повторите пароль</label>
                <input type="password" class="form-control" name="passwordConfirm" placeholder="password" id="password2-input">
                <div class="mt-3 mb-3" style="width: 100%; height: 15px; border-bottom: 1px solid black; text-align: center">
                    <span style="background-color: white; padding: 0 5px">
                        необязательные поля
                    </span>
                </div>
                <label class="mt-3" for="email-input">Электронная почта</label>
                <input type="text" class="form-control ${(emailError??)?string('is-invalid','')}" name="email" id="email-input"
                       placeholder="example@email.com" value="<#if user??>${user.email}</#if>">
                <#if emailError??>
                <div class="invalid-feedback">
                    ${emailError}
                </div>
                </#if>
                <div>
                    <input type="file" name="file">
                </div>
                <div>
                    <button class="btn btn-sm btn-primary btn-block mt-3" type="submit">Регистрация</button>
                </div>
            </form>

            <#include "macros/alert-danger.ftl">
        </div>
    </div>
</div>
</@h.head>