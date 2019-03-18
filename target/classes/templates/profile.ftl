<#import "macros/head.ftl" as h>

<#include "macros/security.ftl">

<@h.head header="Профиль" font1="normal" font2="normal" font3="bold">
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
        <#if user.getEmail() = "">
        Электронная почта: не привязана
        <#else>
        Электронная почта: ${user.getUsername()}
        </#if>
    </div>
</div>
</@h.head>