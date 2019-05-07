<#import "macros/head.ftl" as h>

<@h.head font1 = "normal" font2 = "normal" font3 = "normal" header = "Создание новой темы">
<div class="row justify-content-center">
    <h6 style="font-size: 24px">Создание новой темы</h6>
</div>
<div class="row justify-content-center mt-3 ml-5 mr-5">
    <div class="btn-group w-100">
        <button type="button" disabled class="btn btn-outline-dark" style="width: inherit; font-weight: bold" id="categories">Алгебра</button>
        <button type="button" class="btn btn-outline-dark dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        </button>
        <div class="dropdown-menu dropdown-menu-right" style="width: inherit">
            <a class="dropdown-item" href="#" id="0">Алгебра</a>
            <a class="dropdown-item" href="#" id="1">Линейная алгебра</a>
            <a class="dropdown-item" href="#" id="2">Геометрия</a>
            <a class="dropdown-item" href="#" id="3">Дискретная математика</a>
            <a class="dropdown-item" href="#" id="4">Теория вероятностей</a>
            <a class="dropdown-item" href="#" id="5">Математическая статистика</a>
        </div>
    </div>
</div>
<form action="/forum/create-theme" method="post" enctype="multipart/form-data">
    <div class="row rounded mt-3 ml-5 mr-5">
        <input class="form-control border border-secondary" placeholder="Название темы" onkeyup="listenerForName()"
               maxlength="30" id="name" name="name">
        <div class="invalid-feedback">
            Недопустимая длина (от 5 до 30 символов)
        </div>
    </div>
    <div class="row rounded mt-3 ml-5 mr-5">
        <textarea class="form-control border border-secondary w-100" rows="15" style="resize: none" name="text"
                  placeholder="Сообщение темы" onkeyup="listenerForText()" maxlength="250" id="text"></textarea>
        <div class="invalid-feedback">
            Недопустимая длина (от 10 до 250 символов)
        </div>
    </div>
    <div class="row mt-3 ml-5 mr-5">
        <div class="col p-0">
            <input type="hidden" name="_csrf" id="csrf" value="${_csrf.token}">
            <input type="hidden" name="category" id="category-value">
            <label class="btn btn-success">Прикрепить файл<input type="file" name="file" hidden></label>
        </div>
        <div class="d-flex flex-row-reverse">
            <button type="submit" class="btn btn-success" style="height: 38px" id="btn" disabled>Создать тему</button>
        </div>
    </div>
</form>

<script src="../js/theme-scripts.js"></script>
<script src="../js/validation.js"></script>
</@h.head>