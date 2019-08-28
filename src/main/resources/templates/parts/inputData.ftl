<#macro inputData path isRegisterForm>

<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">User Name :</label>
        <div class="col-sm-6">
            <input type="text" name="username" class="form-control" placeholder="User name" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password :</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Password" />
        </div>
    </div>
    <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email :</label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" placeholder="Email" />
            </div>
        </div>
    </#if>
    <!-- security_token only to post-methods -->
    <input type="hidden" name="_csrf" value="${_csrf.token}" />

    <#if !isRegisterForm><a href="/registration">Registration</a></#if>

    <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
</form>

</#macro>