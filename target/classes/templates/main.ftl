<#import "macros/head.ftl" as h>
<#import "macros/colsAndRowsCount.ftl" as c>
<#import "macros/script.ftl" as s>
<#import "macros/jquery.ftl" as j>

<@h.head font1="bold" font2="normal">
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
                    <a class="dropdown-item" href="#" id="7">Транспонироват</a>
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
        <button type="button" id="btn" class="btn btn-success btn-sm ml-3" style="height: 31px">Вычислить</button>
        <p>
            <a class="btn btn-success btn-sm ml-3" data-toggle="collapse" href="#collapse" role="button" aria-expanded="false" aria-controls="collapse">
                Размеры матриц
            </a>
        </p>
    </div>
    <div class="row">
        <div class="collapse" id="collapse">
            <div class="card card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-sm">
                        <thead>
                        <tr></tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="col">Кол-во столбцов</th>
                            <td>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <button type="button" id="btn-cols-count-less" class="btn btn-outline-success btn-sm" style="width: 31px">-</button>
                                    </div>
                                    <input type="text" id="cols" readonly style="width: 31px; height: 31px" class="form-control p-1 pl-2" value="1">
                                    <div class="input-group-append">
                                        <button type="button" id="btn-cols-count-more" class="btn btn-outline-success btn-sm" style="width: 31px">+</button>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">-</button>
                                    </div>
                                    <input type="text" readonly style="width: 31px; height: 31px" class="form-control p-1 pl-2" value="1">
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">+</button>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">-</button>
                                    </div>
                                    <input type="text" readonly style="width: 31px; height: 31px" class="form-control p-1 pl-2" value="1">
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">+</button>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">-</button>
                                    </div>
                                    <input type="text" readonly style="width: 31px; height: 31px" class="form-control p-1 pl-2" value="1">
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">+</button>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">-</button>
                                    </div>
                                    <input type="text" readonly style="width: 31px; height: 31px" class="form-control p-1 pl-2" value="1">
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">+</button>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">-</button>
                                    </div>
                                    <input type="text" readonly style="width: 31px; height: 31px" class="form-control p-1 pl-2" value="1">
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">+</button>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">-</button>
                                    </div>
                                    <input type="text" readonly style="width: 31px; height: 31px" class="form-control p-1 pl-2" value="1">
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">+</button>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">-</button>
                                    </div>
                                    <input type="text" readonly style="width: 31px; height: 31px" class="form-control p-1 pl-2" value="1">
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">+</button>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">-</button>
                                    </div>
                                    <input type="text" readonly style="width: 31px; height: 31px" class="form-control p-1 pl-2" value="1">
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">+</button>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">-</button>
                                    </div>
                                    <input type="text" readonly style="width: 31px; height: 31px" class="form-control p-1 pl-2" value="1">
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-outline-success btn-sm" style="width: 31px">+</button>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th scope="col">Кол-во строк</th>
                            <td>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <button type="button" id="btn-rows-count-less" class="btn btn-outline-success btn-sm" style="width: 31px">-</button>
                                    </div>
                                    <input type="text" id="rows" readonly style="width: 31px; height: 31px" class="form-control p-1 pl-2" value="1">
                                    <div class="input-group-append">
                                        <button type="button" id="btn-rows-count-more" class="btn btn-outline-success btn-sm" style="width: 31px">+</button>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div id="inp0" class="d-inline-flex flex-column p-1">
        <div id="inp00" class="d-inline-flex flex-row">
            <input type="text" id="number000" style="width: 50px">
        </div>
    </div>
    <div id="op0" class="d-inline-flex flex-column p-1">+</div>
    <div id="inp1" class="d-inline-flex flex-column p-1">
        <div id="inp10" class="d-inline-flex flex-row">
            <input type="text" id="number100" style="width: 50px">
        </div>
    </div>
    <div id="op1" class="d-inline-flex flex-column p-1"></div>
    <div id="inp2" class="d-inline-flex flex-column p-1"></div>
    <div id="op2" class="d-inline-flex flex-column p-1"></div>
    <div id="inp3" class="d-inline-flex flex-column p-1"></div>
    <div id="op3" class="d-inline-flex flex-column p-1"></div>
    <div id="inp4" class="d-inline-flex flex-column p-1"></div>
    <div id="op4" class="d-inline-flex flex-column p-1"></div>
    <div id="inp5" class="d-inline-flex flex-column p-1"></div>
    <div id="op5" class="d-inline-flex flex-column p-1"></div>
    <div id="inp6" class="d-inline-flex flex-column p-1"></div>
    <div id="op6" class="d-inline-flex flex-column p-1"></div>
    <div id="inp7" class="d-inline-flex flex-column p-1"></div>
    <div id="op7" class="d-inline-flex flex-column p-1"></div>
    <div id="inp8" class="d-inline-flex flex-column p-1"></div>
    <div id="op8" class="d-inline-flex flex-column p-1"></div>
    <div id="inp9" class="d-inline-flex flex-column p-1"></div>
</form>
<table class="table table-bordered table-sm" id="result"></table>
<script>
    <@s.script></@s.script>
    <@j.jquery></@j.jquery>
</script>
</@h.head>