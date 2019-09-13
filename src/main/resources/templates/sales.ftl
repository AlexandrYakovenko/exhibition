<#import "parts/page.ftl" as p>
<#include "parts/security.ftl">

<@p.page>
    <div class="container">
        <div class="row">
            <div class="column">
                <h5>${username}</h5>
                <h5>Balance : ${money}</h5>
            </div>
        </div>
        <div class="row">
            <a class="btn btn-primary" href="/sales/addMoney/${user.id}">Add Money</a>
        </div>
        <div class="row">
            <div class="column">
                <div class="card-columns mt-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${exhibition.name}</h5>
                            <h6 class="card-subtitle mb-2 text-muted">Showroom : ${exhibition.showroom}</h6>
                             <p class="card-text">${exhibition.description}</p><br/>
                             <a href="/sales/${exhibition.id}" class="card-link">Buy Ticket</a>
                        </div>
                        <div class="card-footer text-muted">
                             <a href="/user-exhibitions/${exhibition.author.id}">${exhibition.authorName}</a>
                        </div>
                   </div>
                </div>
            </div>
        </div>
    </div>
</@p.page>