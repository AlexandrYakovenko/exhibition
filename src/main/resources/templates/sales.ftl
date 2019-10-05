<#import "parts/page.ftl" as p>
<#include "parts/security.ftl">

<@p.page>

    <div class="container">
        <#if buyError??>
            <div class="alert alert-danger" role="alert">
               ${buyError}
            </div>
        </#if>

        <div class="row">
            <div class="column">
                <h5><@spring.message "label.username"/>${username}</h5>
                <h5><@spring.message "label.balance"/>${money}</h5>
            </div>
        </div>

        <div class="row">
            <a class="btn btn-primary" href="/sales/addMoney/${user.id}">
                <@spring.message "link.add_money"/>
            </a>
        </div>

        <div class="row">
            <div class="column">
                <div class="card-columns mt-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">
                                ${exhibition.name}
                            </h5>
                            <h6 class="card-subtitle mb-2 text-muted">
                                <@spring.message "label.showroom"/>${exhibition.showroom}
                            </h6>
                            <h6 class="card-subtitle mb-2 ">
                                <@spring.message "label.price"/>${exhibition.price}
                            </h6>
                             <p class="card-text">
                                 ${exhibition.description}
                             </p><br/>
                            <h6 class="card-subtitle mb-2 ">
                                <@spring.message "label.date"/>${exhibition.date}
                            </h6>
                        </div>
                        <div class="card-footer text-muted">
                             <a href="/user-exhibitions/${exhibition.author.id}">
                                 ${exhibition.authorName}
                             </a>
                        </div>
                   </div>
                </div>
            </div>
        </div>

        <div class="row">
            <form action="/salesUser/${user.id}" method="post">

                <input type="hidden" name="salesId" value="${exhibition.id}" />

                <!-- security_token only to post-methods -->
                <input type="hidden" name="_csrf" value="${_csrf.token}" />

                <button class="btn btn-success mt-2" type="submit">
                    <@spring.message "button.buy"/>
                </button>
            </form>
        </div>
    </div>

</@p.page>