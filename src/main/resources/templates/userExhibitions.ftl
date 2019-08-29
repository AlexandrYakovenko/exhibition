<#import "parts/page.ftl" as p>

<@p.page>
<#if isCurrentUser>
    <#include "parts/exhibitionEdit.ftl"/>
</#if>
    <#include "parts/exhibitionList.ftl"/>

</@p.page>