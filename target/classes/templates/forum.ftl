<#import "macros/head.ftl" as h>

<@h.head font1 = "normal" font2 = "bold" font3 = "normal" header = "Форум">

<form action="/filter" method="post">
    <input type="hidden" name="_csrf" id="csrf" value="${_csrf.token}">
    <div class="row border border-secondary rounded p-3">
        <div class="col-auto p-0">
            <input type="text" class="form-control" placeholder="Критерий поиска" name="filter">
        </div>
        <div class="col p-0">
            <button type="submit" class="btn btn-success ml-3" style="width: 100px">Найти</button>
        </div>
        <div class="d-flex flex-row-reverse">
            <a href="/forum/create-theme" class="btn btn-success">Создать новую тему</a>
        </div>
    </div>
    <div class="row mt-3">
        <div class="col-auto border border-secondary rounded p-3 mr-3" style="font-size: 12pt">
            <div class="d-flex flex-column">
                <label style="font-size: 14pt; font-weight: bold">Категории форума</label>
                <button type="submit" class="btn btn-outline-dark btn-sm btn-block" value="algebra" name="category">Алгебра</button>
                <button type="submit" class="btn btn-outline-dark btn-sm btn-block mt-2" value="linear algebra" name="category">Линейная алгебра</button>
                <button type="submit" class="btn btn-outline-dark btn-sm btn-block mt-2" value="geometry" name="category">Геометрия</button>
                <button type="submit" class="btn btn-outline-dark btn-sm btn-block mt-2" value="discrete math" name="category">Дискретная математика</button>
                <button type="submit" class="btn btn-outline-dark btn-sm btn-block mt-2" value="probability" name="category">Теория вероятностей</button>
                <button type="submit" class="btn btn-outline-dark btn-sm btn-block mt-2" value="statistic" name="category">Математическая статистика</button>
            </div>
        </div>
        <div class="col border border-secondary rounded p-3">
            <div style="overflow-y: auto; height: 70vh">

            </div>
        </div>
    </div>
</form>
</@h.head>