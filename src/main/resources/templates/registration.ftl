<#import "macros/head.ftl" as h>

<@h.head font1 = "normal" font2 = "normal" font3 = "normal" header = "Регистрация">
<div class="border border-secondary rounded p-5 mlr-auto" style="width: 400px">
    <div class="row justify-content-center">
        <div class="col-auto">
            <h6>Регистрация</h6>
        </div>
    </div>
    <div class="row justify-content-center mt-5">
        <#include "macros/alert-danger.ftl">

        <form action="/registration" method="post" enctype="multipart/form-data">
            <input type="hidden" name="_csrf" id="csrf" value="${_csrf.token}">
            <label class="mt-3" for="nickname-input">Никнейм</label>
            <div>
                <input type="text" class="form-control ${(nicknameError??)?string('is-invalid','')}" name="nickname" id="nickname-input"
                       placeholder="nickname" onkeyup="validNickname()" maxlength="15" value="<#if user??>${user.nickname}</#if>">
                <div class="invalid-feedback">
                    Недопустимая длина (от 3 до 15 символов)
                </div>
            </div>
            <#include "macros/login-form.ftl">
            <label class="mt-3" for="password2-input">Повторите пароль</label>
            <div>
                <input type="password" class="form-control ${(passwordConfirmError??)?string('is-invalid','')}" name="passwordConfirm" placeholder="password"
                       id="password2-input" maxlength="25" onkeyup="validPasswordConfirm()">
                <div class="invalid-feedback">
                    Пароли не совпадают
                </div>
            </div>
            <div class="mt-3 mb-3 h-line">
                <span class="ol-text">
                    необязательные поля
                </span>
            </div>
            <label class="mt-3" for="email-input">Электронная почта</label>
            <div>
                <input type="text" class="form-control ${(emailError??)?string('is-invalid','')}" name="email" id="email-input"
                       placeholder="example@email.com" maxlength="40" onkeyup="validEmail()" value="<#if user??>${user.email}</#if>">
                <div class="invalid-feedback">
                    Почта введена некорректно
                </div>
            </div>
            <div>
                <input type="file" name="file">
            </div>
            <div>
                <button class="btn btn-sm btn-primary btn-block mt-3" type="submit" id="btn" disabled>Регистрация</button>
            </div>
        </form>
    </div>
</div>

<script src="../js/user-valid.js"></script>
<script src="../js/validation.js"></script>
</@h.head>