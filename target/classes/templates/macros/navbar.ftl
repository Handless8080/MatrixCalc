<#include "security.ftl">

<nav class="navbar navbar-expand-lg navbar-light mb-5" style="background-color: seagreen">
    <a class="navbar-brand" style="font-size: 22pt; font-weight: bold"><span style="color: darkviolet">Matrix</span><span style="color: black">Calculator</span>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar" aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbar">
        <div class="navbar-nav" style="font-size: 14pt">
            <a class="nav-link nav-item text-white" href="/" style="font-weight: ${font1}">Калькулятор</a>
            <a class="nav-link nav-item text-white" href="/forum/alg" style="font-weight: ${font2}">Форум</a>
        </div>
        <div class="navbar-nav ml-auto">
            <#if !user??>
            <a class="nav-link nav-item" href="/login" style="color: lightblue; font-size: 12pt; font-weight: ${font3}">Авторизация</a>
            <#else>
            <a class="nav-link nav-item mr-3" href="/profile" style="color: lightblue; font-size: 12pt; font-weight: ${font3}">${user.getNickname()}#${user.getId()}</a>
            <form action="/logout" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button class="btn btn-outline-warning">Выйти</button>
            </form>
            </#if>
        </div>
    </div>
</nav>