<#macro div id>
<div id="inp${id}" class="d-inline-flex flex-column p-1">
    <center class="mb-2">${id + 1}</center>
    <div id="inp${id}0" class="d-inline-flex flex-row">
        <input type="text" id="number${id}00" style="width: 50px">
    </div>
</div>
</#macro>