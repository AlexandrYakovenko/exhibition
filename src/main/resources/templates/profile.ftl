<#import "parts/page.ftl" as p>

<@p.page>

<h5>${username}</h5>

    <form  method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name :</label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="User name" value="${username}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password :</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password" />
            </div>
        </div>
        <!-- security_token only to post-methods -->
        <input type="hidden" name="_csrf" value="${_csrf.token}" />


        <button class="btn btn-primary" type="submit">Save</button>
    </form>

</@p.page>