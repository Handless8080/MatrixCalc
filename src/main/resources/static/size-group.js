function createSizeInput(id, i) {
    var tr = document.getElementById(id + 's');

    var select = document.createElement('td');
    select.id = id == "col" ? "ctd" + i : "rtd" + i;

    var div = document.createElement('div');
    div.classList.add('input-group');
    div.style.minWidth = "91px";

    var prependDiv = document.createElement('div');
    prependDiv.classList.add('input-group-prepend');

    var btnLess = createButton(id, i, 'less', '-');
    prependDiv.appendChild(btnLess);
    div.appendChild(prependDiv);

    var input = document.createElement('input');
    input.type = "text";
    input.id = id + i;
    input.setAttribute("readonly", "true");
    input.style.width = "31px";
    input.style.height = "31px";
    input.classList.add('form-control');
    input.classList.add('p-1');
    input.classList.add('pl-2');
    div.appendChild(input);

    var appendDiv = document.createElement('div');
    appendDiv.classList.add('input-group-append');

    var btnMore = createButton(id, i, 'more', '+');
    appendDiv.appendChild(btnMore);
    div.appendChild(appendDiv);

    select.appendChild(div);
    tr.appendChild(select);

    if (id == "col") {
        btnLess.addEventListener('click', deleteColumns);
        btnMore.addEventListener('click', createColumns);
    } else {
        btnLess.addEventListener('click', deleteRows);
        btnMore.addEventListener('click', createRows);
    }

    var col = document.getElementById('col' + (parseInt(i, 10) - 1)).value;
    input.value = col;
}

function createButton(id, i, type, operator) {
    var btn = document.createElement('button');
    btn.type = "button";
    btn.id = "btn-" + id + "s-count-" + type + i;
    btn.classList.add('btn');
    btn.classList.add('btn-outline-success');
    btn.classList.add('btn-sm');
    btn.style.width = "31px";
    btn.innerHTML = operator;
    return btn;
}