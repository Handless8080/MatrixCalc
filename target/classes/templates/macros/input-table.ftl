<#macro input number>
<div id="op${number - 1}" class="d-inline-flex flex-column p-1"></div>
<div id="inp${number}" class="d-inline-flex flex-column p-1" style="visibility: hidden">
    <center class="mb-2">${number + 1}</center>
</div>
</#macro>