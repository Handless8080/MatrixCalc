<#import "macros/head.ftl" as h>

<#include "macros/security.ftl">

<@h.head header = "Профиль" font1 = "normal" font2 = "normal" font3 = "bold">
<div class="border border-secondary rounded p-5" style="margin-left: auto; margin-right: auto; width: 600px">
    <div class="row justify-content-center">
        <div class="col-auto">
            <h6 style="font-size: 24px">${name}</h6>
        </div>
    </div>
    <div class="mt-3 mb-4" style="width: 100%; height: 15px; border-bottom: 1px solid black; text-align: center">
        <span style="background-color: white; padding: 0 5px">
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
    <div class="mt-3 mb-4" style="width: 100%; height: 15px; border-bottom: 1px solid black; text-align: center">
        <span style="background-color: white; padding: 0 5px">
            Личные данные
        </span>
    </div>
    <div class="row">
        Логин: ${user.getUsername()}
    </div>
    <div class="row">
        <div class="input-group flex-nowrap p-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="addon-wrapping-nickname" style="width: 80px">Имя</span>
            </div>
            <input value="${user.getNickname()}" type="text" class="form-control" name="nickname" placeholder="nickname" aria-describedby="addon-wrapping-nickname">
        </div>
    </div>
    <div class="row">
        <div class="input-group flex-nowrap p-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="addon-wrapping-mail" style="width: 80px">Почта</span>
            </div>
            <#if user.getEmail()??>
            <input type="email" class="form-control" name="email" placeholder="example@email.com" aria-describedby="addon-wrapping-password">
            <#else>
            <input value="${user.getEmail()}" type="email" class="form-control" name="email" placeholder="example@email.com" aria-describedby="addon-wrapping-password">
            </#if>
        </div>
    </div>
    <div class="row">
        <div class="input-group flex-nowrap p-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="addon-wrapping-password" style="width: 80px">Пароль</span>
            </div>
            <input value="${user.getPassword()}" type="password" class="form-control" name="password" placeholder="password" aria-describedby="addon-wrapping-password">
        </div>
    </div>
    <div class="row">
        <input type="file" name="file">
    </div>
</div>
</@h.head>