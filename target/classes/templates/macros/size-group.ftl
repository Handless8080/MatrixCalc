<#macro size dimension id display>
<td id="td-${dimension}${id}" style="display: ${display}">
    <div class="input-group" style="min-width: 91px">
        <div class="input-group-prepend">
            <button type="button" id="btn-${dimension}s-count-less${id}" class="btn btn-outline-success btn-sm" style="width: 31px">-</button>
        </div>
        <input type="text" id="${dimension}${id}" readonly style="width: 31px; height: 31px" class="form-control p-1 pl-2" value="1">
        <div class="input-group-append">
            <button type="button" id="btn-${dimension}s-count-more${id}" class="btn btn-outline-success btn-sm" style="width: 31px">+</button>
        </div>
    </div>
</td>
</#macro>