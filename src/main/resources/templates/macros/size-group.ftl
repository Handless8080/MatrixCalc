<#macro input header dimension>
<th scope="col">
    <div style="min-width: 140px">${header}</div>
</th>
<td>
    <div class="input-group" style="min-width: 91px">
        <div class="input-group-prepend">
            <button type="button" id="btn-${dimension}s-count-less0" class="btn btn-outline-success btn-sm" style="width: 31px">-</button>
        </div>
        <input type="text" id="${dimension}0" readonly style="width: 31px; height: 31px" class="form-control p-1 pl-2" value="1">
        <div class="input-group-append">
            <button type="button" id="btn-${dimension}s-count-more0" class="btn btn-outline-success btn-sm" style="width: 31px">+</button>
        </div>
    </div>
</td>
</#macro>