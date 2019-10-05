<#import "parts/page.ftl" as p>

<@p.page>

    <h5><@spring.message "label.username"/>${user.username}</h5>

    <h5><@spring.message "label.tickets"/></h5>

    <#if tickets??>
        <#list tickets as ticket>
         <div class="container">
             <table class="table table-striped">
                 <thead>
                     <tr>
                         <th scope="col"><@spring.message "label.exhibition_name"/></th>
                         <th scope="col"><@spring.message "label.exhibition_showroome"/></th>
                         <th scope="col"><@spring.message "label.exhibition_description"/></th>
                         <th scope="col"><@spring.message "label.exhibition_price"/></th>
                         <th scope="col"><@spring.message "label.exhibition_date"/></th>
                     </tr>
                 </thead>
                    <hr/>
                 <tbody>
                     <tr>
                         <td scope="row">${ticket.name}</td>
                         <td>${ticket.showroom}</td>
                         <td>${ticket.description}</td>
                         <td>${ticket.price}</td>
                         <td>${ticket.date}</td>
                     </tr>
                 </tbody>
             </table>
         </div>
         </#list>
     </#if>

</@p.page>