<#import "macros/head.ftl" as h>
<#import "macros/div.ftl" as d>
<#import "macros/size-group.ftl" as s>
<#import "macros/input-table.ftl" as i>

<@h.head flag="true" header="Калькулятор" font1="bold" font2="normal">
<form method="post" action="answer">
    <input type="hidden" name="_csrf" id="csrf" value="${_csrf.token}">
    <div class="row">
        <div class="col-mx-5 mr-3">
            <span>Операция:</span>
            <div class="btn-group">
                <button type="button" id="operators" class="btn btn-sm btn-outline-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Сложение</button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#" id="1">Сложение</a>
                    <a class="dropdown-item" href="#" id="2">Вычитание</a>
                    <a class="dropdown-item" href="#" id="3">Умножение</a>
                    <a class="dropdown-item" href="#" id="4">Возведение в степень</a>
                    <a class="dropdown-item" href="#" id="5">Найти определитель</a>
                    <a class="dropdown-item" href="#" id="6">Найти обратную</a>
                    <a class="dropdown-item" href="#" id="7">Транспонировать</a>
                    <a class="dropdown-item" href="#" id="8">Найти ранг</a>
                </div>
            </div>
        </div>
        <div class="col-mx-5">
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="mr-1">Кол-во матриц:</span>
                    <button type="button" id="btn-matr-count-less" class="btn btn-outline-success btn-sm" style="width: 31px">-</button>
                </div>
                <input type="text" id="matr-count" readonly class="form-control p-1 pl-2" style="width: 31px; height: 31px" value="2">
                <div class="input-group-prepend">
                    <button type="button" id="btn-matr-count-more" class="btn btn-outline-success btn-sm" style="width: 31px">+</button>
                </div>
            </div>
        </div>
    </div>
    <div class="row mb-3 mt-3">
        <div style="overflow-x: auto">
            <table class="table table-bordered table-sm">
                <thead>
                <tr id="header" style="display: none">
                    <th scope="col">Номер матрицы</th>
                    <th id="s-head0" scope="col" style="display: none"><center>1</center></th>
                    <th id="s-head1" scope="col" style="display: none"><center>2</center></th>
                    <th id="s-head2" scope="col" style="display: none"><center>3</center></th>
                    <th id="s-head3" scope="col" style="display: none"><center>4</center></th>
                    <th id="s-head4" scope="col" style="display: none"><center>5</center></th>
                    <th id="s-head5" scope="col" style="display: none"><center>6</center></th>
                    <th id="s-head6" scope="col" style="display: none"><center>7</center></th>
                    <th id="s-head7" scope="col" style="display: none"><center>8</center></th>
                    <th id="s-head8" scope="col" style="display: none"><center>9</center></th>
                    <th id="s-head9" scope="col" style="display: none"><center>10</center></th>
                </tr>
                </thead>
                <tbody>
                <tr id="cols">
                    <th scope="col" id="col-header">
                        <div style="min-width: 140px">Кол-во столбцов</div>
                    </th>
                    <@s.size dimension="col" id=0 display="table-cell"></@s.size>
                    <@s.size dimension="col" id=1 display="none"></@s.size>
                    <@s.size dimension="col" id=2 display="none"></@s.size>
                    <@s.size dimension="col" id=3 display="none"></@s.size>
                    <@s.size dimension="col" id=4 display="none"></@s.size>
                    <@s.size dimension="col" id=5 display="none"></@s.size>
                    <@s.size dimension="col" id=6 display="none"></@s.size>
                    <@s.size dimension="col" id=7 display="none"></@s.size>
                    <@s.size dimension="col" id=8 display="none"></@s.size>
                    <@s.size dimension="col" id=9 display="none"></@s.size>
                </tr>
                <tr id="rows">
                    <th scope="col" id="row-header">
                        <div style="min-width: 140px">Кол-во строк</div>
                    </th>
                    <@s.size dimension="row" id=0 display="table-cell"></@s.size>
                    <@s.size dimension="row" id=1 display="none"></@s.size>
                    <@s.size dimension="row" id=2 display="none"></@s.size>
                    <@s.size dimension="row" id=3 display="none"></@s.size>
                    <@s.size dimension="row" id=4 display="none"></@s.size>
                    <@s.size dimension="row" id=5 display="none"></@s.size>
                    <@s.size dimension="row" id=6 display="none"></@s.size>
                    <@s.size dimension="row" id=7 display="none"></@s.size>
                    <@s.size dimension="row" id=8 display="none"></@s.size>
                    <@s.size dimension="row" id=9 display="none"></@s.size>
                </tr>
                <tr id="params" style="display: none">
                    <th scope="col" id="param-header">Степень</th>
                    <@s.size dimension="param" id=0 display="none"></@s.size>
                    <@s.size dimension="param" id=1 display="none"></@s.size>
                    <@s.size dimension="param" id=2 display="none"></@s.size>
                    <@s.size dimension="param" id=3 display="none"></@s.size>
                    <@s.size dimension="param" id=4 display="none"></@s.size>
                    <@s.size dimension="param" id=5 display="none"></@s.size>
                    <@s.size dimension="param" id=6 display="none"></@s.size>
                    <@s.size dimension="param" id=7 display="none"></@s.size>
                    <@s.size dimension="param" id=8 display="none"></@s.size>
                    <@s.size dimension="param" id=9 display="none"></@s.size>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="d-inline-flex flex-row" style="overflow-x: auto">
            <@d.div id=0></@d.div>
            <div id="op0" class="d-inline-flex flex-column p-1">+</div>
            <@d.div id=1></@d.div>
            <@i.input number=2></@i.input>
            <@i.input number=3></@i.input>
            <@i.input number=4></@i.input>
            <@i.input number=5></@i.input>
            <@i.input number=6></@i.input>
            <@i.input number=7></@i.input>
            <@i.input number=8></@i.input>
            <@i.input number=9></@i.input>
        </div>
    </div>
    <div class="row mt-3">
        <button type="button" id="btn" class="btn btn-sm btn-success">Вычислить</button>
    </div>
</form>
<div class="row mt-3">
    <div style="overflow-x: auto" id="answer">
        <table class="table table-bordered table-sm" id="result"></table>
    </div>
</div>

<script src="static/operator.js"></script>
<script src="static/table.js"></script>
<script src="static/rows.js"></script>
<script src="static/cols.js"></script>
<script src="static/scripts.js"></script>
<script src="static/show-answer.js"></script>
</@h.head>