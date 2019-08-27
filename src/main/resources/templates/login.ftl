<#import "parts/page.ftl" as p>
<#import "parts/inputData.ftl" as login>

<@p.page>

Login page

<@login.inputData "/login" false/>

<a href="/registration">Add new user</a>

</@p.page>