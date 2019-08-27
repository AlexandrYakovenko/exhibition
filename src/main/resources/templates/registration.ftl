<#import "parts/page.ftl" as p>
<#import "parts/inputData.ftl" as registration>

<@p.page>
Add new user

<@registration.inputData "/registration" true/>

</@p.page>