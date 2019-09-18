<#include "security.ftl">
<#import "pager.ftl" as p>

<#if page??>
        <div class="card-columns mt-3">
            <#list page.content as exhibition>
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${exhibition.name}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">Showroom : ${exhibition.showroom}</h6>
                        <h6 class="card-subtitle mb-2 ">Price : ${exhibition.price}</h6>
                        <p class="card-text">${exhibition.description}</p><br/>
                        <h6 class="card-subtitle mb-2 ">Date : ${exhibition.date}</h6>
                        <a href="/sales/${user.id}/${exhibition.id}" class="card-link">Buy Ticket</a>
                    </div>
                    <div class="card-footer text-muted container">
                        <div class="row">
                            <a class="col align-self-center" href="/user-exhibitions/${exhibition.author.id}">${exhibition.authorName}</a>
                            <p class="col align-self-center"></p>
                            <#if exhibition.author.id == currentUserId>
                                <a class="col align-self-center btn btn-secondary" href="/user-exhibitions/${exhibition.author.id}?exhibition=${exhibition.id}">
                                    Edit
                                </a>
                            </#if>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
</#if>

<@p.pager url page/>
