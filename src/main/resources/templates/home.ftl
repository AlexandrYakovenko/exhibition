<#import "parts/page.ftl" as p>
<#include "parts/security.ftl">

<@p.page>

<h5>
    <#if user??>
        <@spring.message "home.hello"/>, ${user.username}
    <#else>
        <@spring.message "home.hello.guest" />
    </#if>
</h5>
</@p.page>