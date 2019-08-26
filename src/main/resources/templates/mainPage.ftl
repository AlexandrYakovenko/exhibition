<html>
<body>
    <form method="post">
        <input type="text" name="name" placeholder="name of exhibition">
        <input type="text" name="showroom" placeholder="name of showroom">
        <button type="submit">Submit</button>
    </form>
    <form method="post" action="filter">
        <input type="text" name="showroom" placeholder="showroom">
        <button type="submit">Submit</button>
    </form>
<div>
    <#if exhibitions??>
        <#list exhibitions as exhibition>
                <span>${exhibition.id}</span>
                <span>${exhibition.name}</span>
                <span>${exhibition.showroom}</span><br/>
        </#list>
    <#else>
        No Message
    </#if>
</div>
</body>
</html>