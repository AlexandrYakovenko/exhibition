<#import "parts/page.ftl" as p>
<#import "parts/inputData.ftl" as login>

<@p.page>

    ${message!}

    <@login.inputData "/login" false/>

</@p.page>