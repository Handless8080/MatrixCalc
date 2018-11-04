<#import "macros/head.ftl" as h>
<#import "macros/colsAndRowsCount.ftl" as c>
<#import "macros/script.ftl" as s>
<#import "macros/jquery.ftl" as j>

<@h.head>
<form method="post" id="postForm">
    <div>
        <span>Операция:</span>
        <select id="operators" name="operators" onchange="setOperator()">
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
        <select id="matrCount" name="matrCount" onchange="setTablesCount()">
            <@c.count>selected</@c.count>
        </select>
        <span>Кол-во столбцов:</span>
        <select id="rows" name="rows" onchange="createInputTable(document.getElementById('matrCount').value)">
            <@c.count></@c.count>
        </select>
        <span>строк:</span>
        <select id="cols" name="cols" onchange="createInputTable(document.getElementById('matrCount').value)">
            <@c.count></@c.count>
        </select>
        <button type="submit" id="btnSubmit">Вычислить</button>
    </div>
    <div id="inp0" style="float: left">
        <input type="text" name="number" style="width: 50px">
    </div>
    <div id="op0" style="float: left">+</div>
    <div id="inp1" style="float: left">
        <input type="text" name="number" style="width: 50px">
    </div>
    <div id="op1" style="float: left"></div>
    <div id="inp2" style="float: left"></div>
    <div id="op2" style="float: left"></div>
    <div id="inp3" style="float: left"></div>
    <div id="op3" style="float: left"></div>
    <div id="inp4" style="float: left"></div>
    <div id="op4" style="float: left"></div>
    <div id="inp5" style="float: left"></div>
    <div id="op5" style="float: left"></div>
    <div id="inp6" style="float: left"></div>
    <div id="op6" style="float: left"></div>
    <div id="inp7" style="float: left"></div>
    <div id="op7" style="float: left"></div>
    <div id="inp8" style="float: left"></div>
    <div id="op8" style="float: left"></div>
    <div id="inp9" style="float: left"></div>
</form>
<div id="result"></div>
<table border="1" id="container">
    <#if answer??>
        <#list answer as row>
        <tr>
            <#list row as cell>
                <td>${cell}</td>
            </#list>
        </#list>
    </#if>
</table>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
    <@s.script></@s.script>
    <@j.jquery></@j.jquery>
</script>
</@h.head>