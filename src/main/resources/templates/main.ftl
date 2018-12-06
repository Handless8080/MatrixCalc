<#import "macros/head.ftl" as h>
<#import "macros/div.ftl" as d>
<#import "macros/size-group.ftl" as i>

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
                <input type="text" id="matr-count" readonly style="width: 31px; height: 31px" class="form-control p-1 pl-2" value="2">
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
                <tr id="header"></tr>
                </thead>
                <tbody>
                <tr id="cols">
                    <@i.input header="Кол-во столбцов" dimension="col"></@i.input>
                </tr>
                <tr id="rows">
                    <@i.input header="Кол-во строк" dimension="row"></@i.input>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="d-inline-flex flex-row" style="overflow-x: auto">
            <@d.div id="0" number="1"></@d.div>
            <div id="op0" class="d-inline-flex flex-column p-1">+</div>
            <@d.div id="1" number="2"></@d.div>
            <#include "macros/input-table.ftl">
        </div>
    </div>
</form>
<table class="table table-bordered table-sm" id="result"></table>
<script src="static/show-answer.js"></script>
<script src="static/operator.js"></script>
<script src="static/table.js"></script>
<script src="static/rows.js"></script>
<script src="static/cols.js"></script>
<script src="static/size-group.js"></script>
<script src="static/scripts.js"></script>
</@h.head>