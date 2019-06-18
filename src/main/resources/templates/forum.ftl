<#import "macros/head.ftl" as h>

<@h.head font1 = "normal" font2 = "bold" font3 = "normal" header = "Форум">

<form action="/forum/${category}/filter" method="post">
    <input type="hidden" name="_csrf" id="csrf" value="${_csrf.token}">
    <div class="row border border-secondary rounded p-3">
        <div class="col-auto p-0">
            <input type="text" class="form-control" placeholder="Критерий поиска" name="filter"
                    value="<#if filter??>${filter}</#if>">
        </div>
        <div class="col p-0">
            <button type="submit" class="btn btn-success ml-3" style="width: 100px">Найти</button>
        </div>
        <div class="d-flex flex-row-reverse">
            <a href="/forum/create-theme" class="btn btn-success">Создать новую тему</a>
        </div>
    </div>
</form>
<div class="row mt-3" style="height: 75vh">
    <div class="col-auto border border-secondary rounded p-3 mr-3" style="font-size: 12pt">
        <div class="d-flex flex-column">
            <label style="font-size: 14pt; font-weight: bold">Категории форума</label>
            <a id="0" href="/forum/alg" class="btn btn-dark btn-sm btn-block text-light">Алгебра</a>
            <a id="1" href="/forum/lalg" class="btn btn-outline-dark btn-sm btn-block mt-2">Линейная алгебра</a>
            <a id="2" href="/forum/geom" class="btn btn-outline-dark btn-sm btn-block mt-2">Геометрия</a>
            <a id="3" href="/forum/dmath" class="btn btn-outline-dark btn-sm btn-block mt-2">Дискретная математика</a>
            <a id="4" href="/forum/theor" class="btn btn-outline-dark btn-sm btn-block mt-2">Теория вероятностей</a>
            <a id="5" href="/forum/mathstat" class="btn btn-outline-dark btn-sm btn-block mt-2">Математическая статистика</a>
        </div>
    </div>
    <div class="col border border-secondary rounded p-3">
        <div style="overflow-y: auto">
            <#list themes as theme>
            <div class="d-flex flex-row">
                <a href="/forum/theme/${theme.id}">${theme.name}</a>
                <div class="ml-auto">
                    <span>${theme.creationDate}</span>
                </div>
            </div>
            <div class="d-flex flex-row">
                <span>Рейтинг: <span class="<#if theme.rate gte 0>text-success<#else>text-danger</#if>">${theme.rate}</span></span>
                <div class="ml-auto">
                    <span>Автор: <a href="/profile/${theme.author.id}">${theme.author.nickname}#${theme.author.id}</a></span>
                </div>
            </div>
            <div class="h-line mb-3"></div>
            <#else>
            <span>Тем не найдено</span>
            </#list>
        </div>
    </div>
</div>

<script src="../js/change-category.js"></script>
</@h.head>