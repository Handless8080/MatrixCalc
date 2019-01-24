<#macro div id header>
<div id="inp${id}" class="d-inline-flex flex-column p-1">
    <center class="mb-2">${header}</center>
    <div id="inp${id}0" class="d-inline-flex flex-row">
        <input type="text" id="number${id}00" style="width: 50px">
    </div>
</div>
</#macro>