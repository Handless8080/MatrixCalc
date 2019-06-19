<#import "macros/head.ftl" as h>

<@h.head font1 = "normal" font2 = "normal" font3 = "normal" header = "Форум">
<#if theme??>
<#include "macros/security.ftl">
<div class="row">
    <h3>${theme.name}</h3>
</div>
<input type="hidden" name="_csrf" id="csrf_" value="${_csrf.token}">
<div class="row border border-secondary rounded p-3">
    <div class="col-auto">
        <div class="input-group">
            <div class="input-group-prepend">
                <button type="button" class="btn btn-outline-success btn-sm w-31" id="theme-rate-sub">-</button>
            </div>
            <input type="text" readonly class="form-control p-1 pl-2 h-31 <#if theme.rate gte 0>text-success<#else>text-danger</#if>"
                   value="${theme.rate}" style="width: 31px" id="theme_rate">
            <div class="input-group-append">
                <button type="button" class="btn btn-outline-success btn-sm w-31" id="theme-rate-add">+</button>
            </div>
        </div>
    </div>
    <div class="col ml-3">
        ${theme.text}
    </div>
    <div class="ml-auto p-1" style="background-color: #f9f4de">
        <a class="d-flex flex-row" href="/profile/${theme.author.id}" style="font-color: #5dd3f7">${theme.author.nickname}#${theme.author.id}</a>
        <span class="d-flex flex-row">Отправлено: ${theme.creationDate}</span>
        <span class="d-flex flex-row">Рейтинг пользователя:<span class="<#if theme.author.rate gte 0>text-success<#else>text-danger</#if>">${theme.author.rate}</span></span>
        <#if user?? && !user.isUser()>
        <form action="/delete-theme/${theme.id}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-danger">Удалить тему</button>
        </form>
        </#if>
    </div>
    <input type="hidden" value="${theme.id}" id="theme-id">
</div>
<div class="row mt-3">
    <h4>
        Ответы
    </h4>
</div>
<#list theme.messages as message>
<div class="row border border-secondary rounded p-3" id="div${message.id}">
    <div class="col-auto">
        <div class="input-group">
            <div class="input-group-prepend">
                <button type="button" class="btn btn-outline-success btn-sm w-31" name="message-rate-sub" id="${message.id}sub">-</button>
            </div>
            <input type="text" readonly class="form-control p-1 pl-2 h-31 <#if message.rate gte 0>text-success<#else>text-danger</#if>"
                   value="${message.rate}" style="width: 31px" id="message_rate${message.id}">
            <div class="input-group-append">
                <button type="button" class="btn btn-outline-success btn-sm w-31" name="message-rate-add" id="${message.id}add">+</button>
            </div>
        </div>
    </div>
    <div class="col ml-3">
        ${message.text}
    </div>
    <div class="ml-auto p-1" style="background-color: #f9f4de">
        <a class="d-flex flex-row" href="/profile/${message.author.id}" style="font-color: #5dd3f7">${message.author.nickname}#${message.author.id}</a>
        <span class="d-flex flex-row"><#if message.author.isModer()>(модератор)</#if></span>
        <span class="d-flex flex-row">Отправлено: ${message.creationDate}</span>
        <span class="d-flex flex-row">Рейтинг пользователя:<span class="<#if message.author.rate gte 0>text-success<#else>text-danger</#if>">${message.author.rate}</span></span>
        <#if user?? && !user.isUser()>
        <button type="button" class="btn btn-danger" id="${message.id}" name="message-delete-btn">Удалить сообщение</button>
        </#if>
    </div>
</div>
</#list>
<form action="/forum/theme/${theme.id}" method="post">
    <input type="hidden" name="_csrf" id="csrf" value="${_csrf.token}">
    <div class="div-bottom d-flex flex-row">
        <div class="h-31">
            <input style="display: none" type="file" name="file" id="file">
            <label class="btn btn-success" for="file" id="file-lbl">Прикрепить файл</label>
        </div>
        <input type="text" name="text" class="form-control w-100 mr-1 ml-1">
        <button type="submit" class="btn btn-success">Отправить</button>
    </div>
</form>
<#else>
<h3>Тема не найдена</h3>
</#if>

<script src="../../js/rate.js"></script>
<#if user?? && !user.isUser()>
<script src="../../js/theme-moderation.js"></script>
</#if>
</@h.head>