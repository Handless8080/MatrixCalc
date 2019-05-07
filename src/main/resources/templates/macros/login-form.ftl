<label class="mt-3" for="username-input">Имя пользователя</label>
<div>
    <input type="text" class="form-control ${(usernameError??)?string('is-invalid','')}" name="username" id="username-input"
           placeholder="username" onkeyup="validUsername()" maxlength="15" value="<#if user??>${user.username}</#if>">
    <div class="invalid-feedback">
        Недопустимая длина (от 3 до 15 символов)
    </div>
</div>
<label class="mt-3" for="password-input">Пароль</label>
<div>
    <input type="password" class="form-control ${(passwordError??)?string('is-invalid','')}" name="password" id="password-input"
           placeholder="password" onkeyup="validPassword()" maxlength="25">
    <div class="invalid-feedback">
        Недопустимая длина (от 5 до 25 символов)
    </div>
</div>