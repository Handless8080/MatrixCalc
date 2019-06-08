<#import "macros/head.ftl" as h>

<#include "macros/security.ftl">

<@h.head header = "Профиль" font1 = "normal" font2 = "normal" font3 = "bold">

<div class="border border-secondary rounded p-5 mlr-auto" style="width: 600px">
    <div class="row justify-content-center">
        <h6>${user.getNickname()}</h6>
    </div>
    <div class="row justify-content-center">
        <img src="/images/${user.getAvatarFileName()}" width="200" height="200">
    </div>
    <div class="mt-3 mb-4 h-line">
        <span class="ol-text">
            Общая информация
        </span>
    </div>
    <div class="row">
        Дата создания аккаунта: ${user.getDate()}
    </div>
    <div class="row">
        Кол-во созданных тем: ${user.getThemeCount()}
    </div>
    <div class="row">
        Кол-во отправленных сообщений: ${user.getMessageCount()}
    </div>
    <div class="row">
        Рейтинг пользователя: ${user.getRate()}
    </div>
    <div class="mt-3 mb-4 h-line">
        <span class="ol-text">
            Личные данные
        </span>
    </div>

    <#include "macros/alert-success.ftl">

    <div class="row">
        Имя пользователя: ${user.getUsername()}
    </div>
    <form action="/profile" method="post">
        <input type="hidden" name="_csrf" id="csrf" value="${_csrf.token}">
        <div class="row">
            <div class="input-group pt-3">
                <div class="input-group-prepend">
                    <span class="input-group-text w-90px" id="addon-wrapping-nickname">Никнейм</span>
                </div>
                <input value="${user.getNickname()}" type="text" class="form-control ${(nicknameError??)?string('is-invalid','')}" name="nickname"
                       placeholder="nickname" aria-describedby="addon-wrapping-nickname" id="nickname-input" maxlength="15" onkeyup="validNickname()">
                <div class="invalid-feedback">
                    Недопустимая длина (от 3 до 15 символов)
                </div>
            </div>
        </div>
        <div class="row">
            <div class="input-group pt-3">
                <div class="input-group-prepend">
                    <span class="input-group-text w-90px" id="addon-wrapping-mail">Почта</span>
                </div>
                <input value="${user.getEmail()}" type="text" class="form-control ${(emailError??)?string('is-invalid','')}" name="email"
                       placeholder="example@email.com" aria-describedby="addon-wrapping-mail" id="email-input" maxlength="40" onkeyup="validEmail()">
                <div class="invalid-feedback">
                    <#if emailError??>
                    ${emailError}
                    <#else>
                    Почта введена некорректно
                    </#if>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="input-group pt-3">
                <div class="input-group-prepend">
                    <span class="input-group-text w-90px" id="addon-wrapping-password">Пароль</span>
                </div>
                <input value="${user.getPassword()}" type="password" class="form-control ${(passwordError??)?string('is-invalid','')}" name="password" placeholder="password"
                       aria-describedby="addon-wrapping-password" id="password-input" maxlength="25" onkeyup="validPassword()">
                <div class="invalid-feedback">
                    Недопустимая длина (от 5 до 25 символов)
                </div>
            </div>
        </div>
        <div class="row mt-3">
            <div>
                <input style="display: none" type="file" name="file" id="file">
                <label class="btn btn-sm btn-success" for="file" id="file-lbl">Выбрать файл для аватара</label>
            </div>
        </div>
        <div class="row">
            <button class="btn btn-sm btn-primary mt-3" type="submit" id="btn" disabled>Сохранить</button>
        </div>
    </form>
</div>

<script src="../js/user-valid.js"></script>
<script src="../js/validation.js"></script>
</@h.head>