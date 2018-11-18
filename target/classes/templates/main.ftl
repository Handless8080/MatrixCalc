<#import "macros/head.ftl" as h>
<#import "macros/colsAndRowsCount.ftl" as c>
<#import "macros/script.ftl" as s>
<#import "macros/jquery.ftl" as j>

<@h.head>
<form method="post" action="answer">
    <input type="hidden" name="_csrf" id="csrf" value="${_csrf.token}">
    <div>
        <span>Операция:</span>
        <select id="operators" onchange="setOperator()">
            <option>Сложение</option>
            <option>Вычитание</option>
            <option>Умножение</option>
            <option>Возведение в степень</option>
            <option>Найти определитель</option>
            <option>Найти обратную</option>
            <option>Транспонировать</option>
            <option>Найти ранг</option>
        </select>
        <span>Кол-во матриц:</span>
        <select id="matrCount" onchange="setTablesCount()">
            <@c.count>selected</@c.count>
        </select>
        <span>Кол-во столбцов:</span>
        <select id="rows" onchange="createInputTable(document.getElementById('matrCount').value)">
            <@c.count></@c.count>
        </select>
        <span>строк:</span>
        <select id="cols" onchange="createInputTable(document.getElementById('matrCount').value)">
            <@c.count></@c.count>
        </select>
        <button type="button" id="btn">Вычислить</button>
    </div>
    <div style="height: 330px; overflow-x: scroll; white-space: nowrap">
        <div id="inp0" class="col">
            <input type="text" id="number000" style="width: 50px">
        </div>
        <div id="op0" class="col">+</div>
        <div id="inp1" class="col">
            <input type="text" id="number100" style="width: 50px">
        </div>
        <div id="op1"></div>
        <div id="inp2"></div>
        <div id="op2"></div>
        <div id="inp3"></div>
        <div id="op3"></div>
        <div id="inp4"></div>
        <div id="op4"></div>
        <div id="inp5"></div>
        <div id="op5"></div>
        <div id="inp6"></div>
        <div id="op6"></div>
        <div id="inp7"></div>
        <div id="op7"></div>
        <div id="inp8"></div>
        <div id="op8"></div>
        <div id="inp9"></div>
    </div>
</form>
<table class="table table-bordered table-sm" id="result"></table>
<script>
    <@s.script></@s.script>
    <@j.jquery></@j.jquery>
</script>
</@h.head>