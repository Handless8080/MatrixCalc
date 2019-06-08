<#import "macros/head.ftl" as h>

<@h.head font1 = "normal" font2 = "normal" font3 = "normal" header = "Форум">
    <div class="row">
        <h3>Название темы</h3>
    </div>
    <div class="row border border-secondary rounded p-3">
        <div class="d-flex flex-row">
            <div class="input-group">
                <div class="input-group-prepend">
                    <button type="button" class="btn btn-outline-success btn-sm w-31">-</button>
                </div>
                <input type="text" readonly class="form-control p-1 pl-2 h-31" value="0" style="width: 31px">
                <div class="input-group-prepend">
                    <button type="button" class="btn btn-outline-success btn-sm w-31">+</button>
                </div>
            </div>
        </div>
        <div class="ml-3">
            Сообщение темы
        </div>
    </div>
    <div class="row mt-3">
        <h4>
            Ответы
        </h4>
    </div>
    <div class="row border border-secondary rounded p-3">
        <div class="d-flex flex-row">
            <div class="input-group">
                <div class="input-group-prepend">
                    <button type="button" class="btn btn-outline-success btn-sm w-31">-</button>
                </div>
                <input type="text" readonly class="form-control p-1 pl-2 h-31" value="0" style="width: 31px">
                <div class="input-group-prepend">
                    <button type="button" class="btn btn-outline-success btn-sm w-31">+</button>
                </div>
            </div>
        </div>
        <div class="ml-3">
            Сообщение пользователя
        </div>
    </div>
</@h.head>