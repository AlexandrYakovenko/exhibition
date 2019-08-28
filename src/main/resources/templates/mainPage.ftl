<#import "parts/page.ftl" as p>
<#import "parts/logut.ftl" as logout>
<#include "parts/security.ftl">

<@p.page>
<div class="form-rom">
     <form action="main" method="get" class="form-inline">
         <input type="text" name="showroom" class="form-control" value="${showroom?ifExists}" placeholder="showroom">
         <button type="submit" class="btn btn-primary ml-2">Search</button>
     </form>
</div>
<#if isAdmin>
 <a class="btn btn-primary mt-3" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
     Add new exhibition
 </a>
</#if>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <div class="col-sm-6">
                 <form action="/main" method="post">
                     <div class="form-group">
                        <label for="exhibition">Exhibition</label>
                        <input type="text" name="name" class="form-control" id="exhibition" placeholder="name of exhibition">
                     </div>
                     <div class="form-group">
                         <label for="showroom">Showroom</label>
                         <input type="text" name="showroom" class="form-control" id="showroom" placeholder="name of showroom">
                     </div>
                     <div class="form-group">
                         <label for="description">Description</label>
                        <input type="text" name="description" class="form-control" id="description" placeholder="description of event" />
                     </div>
                        <!-- security_token only to post-methods -->
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                     <div class="form-group">
                        <button type="submit" class="btn btn-primary">Submit</button>
                     </div>
                </form>
            </div>
        </div>
    </div>

    <#if exhibitions??>
        <div class="card-columns mt-3">
            <#list exhibitions as exhibition>
                <div class="card">
                    <div class="card-body">
                            <h5 class="card-title">${exhibition.name}</h5>
                            <h6 class="card-subtitle mb-2 text-muted">${exhibition.showroom}</h6>
                            <#--<p class="card-text">${exhibition.description}</p><br/>-->
                            <a href="/buyTicket" class="card-link">Buy Ticket</a>
                    </div>
                    <div class="card-footer text-muted">
                        ${exhibition.authorName}
                    </div>
                </div>
            </#list>
        </div>
    <#else>
           No Exhibitions
    </#if>

</@p.page>