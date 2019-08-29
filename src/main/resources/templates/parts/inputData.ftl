<#macro inputData path isRegisterForm>

<form action="${path}" method="post">
    <!-- Username -->
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">User Name :</label>
        <div class="col-sm-6">
            <input type="text" name="username" class="form-control ${(usernameError??)?string('is-invalid', '')}"
                   value="<#if user??>${user.username}</#if>" placeholder="Username" />
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                 </div>
            </#if>
        </div>
    </div>

    <!-- Password -->
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password :</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}"
                   placeholder="Password" />
             <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
             </#if>
        </div>
    </div>

    <#if isRegisterForm>
    <!-- Password  Confirmation-->
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password :</label>
            <div class="col-sm-6">
                <input type="password" name="passwordConfirm" class="form-control ${(passwordConfirmError??)?string('is-invalid', '')}"
                       placeholder="Retype Password" />
                 <#if passwordConfirmError??>
                    <div class="invalid-feedback">
                        ${passwordConfirmError}
                    </div>
                 </#if>
            </div>
        </div>

    <!-- reCaptcha-->
        <div class="col-sm-6">
            <div class="g-recaptcha" data-sitekey="6LdXr7QUAAAAAIpZ4oi0pn4jH65MMb_HcSH4vFIS"></div>
            <#if captchaError??>
                <div class="alert alert-danger" role="alert">
                    ${captchaError}
                </div>
            </#if>
        </div>
    </#if>

    <!-- security_token only to post-methods -->
    <input type="hidden" name="_csrf" value="${_csrf.token}" />

    <#if !isRegisterForm><a href="/registration">Registration</a></#if>

    <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
</form>

</#macro>