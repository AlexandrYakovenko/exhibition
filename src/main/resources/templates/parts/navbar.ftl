<#include "security.ftl">
<#import "logut.ftl" as logout>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/main">Exhibitions</a>
            </li>
            <#--<li class="nav-item">
                <a class="nav-link" href="/salesUser/${user.id}">Sales</a>
            </li>-->
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user-exhibitions/${currentUserId}">My Exhibitions</a>
                </li>
            </#if>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Profile</a>
                </li>
            </#if>
            <#if isSuperAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">User List</a>
                </li>
            </#if>
        </ul>


        <div class="navbar-text mr-3"><#if user??>${name}<#else>Please, login</#if></div>
        <a class="btn btn-info mr-1" href="/?lang=ua">UK</a>
        <a class="btn btn-info mr-3" href="/?lang=en">EN</a>
        <#include "logut.ftl"/>
    </div>
</nav>