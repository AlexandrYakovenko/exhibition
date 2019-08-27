<html>
<body>
<div>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" value="Sign Out"/>
    </form>
</div>
    <form action="/main" method="post">
        <input type="text" name="name" placeholder="name of exhibition">
        <input type="text" name="showroom" placeholder="name of showroom">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Submit</button>
    </form>
    <form method="post" action="filter">
        <input type="text" name="showroom" placeholder="showroom">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Submit</button>
    </form>
<div>
    <#if exhibitions??>
        <#list exhibitions as exhibition>
                <span>${exhibition.id}</span>
                <span>${exhibition.name}</span>
                <span>${exhibition.showroom}</span>
                <span>${exhibition.authorName}</span><br/>
        </#list>
    <#else>
        No Message
    </#if>
</div>
</body>
</html>