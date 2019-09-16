<#import "parts/page.ftl" as p>

<!--TODO сделать красиво страницу -->
<@p.page>
     <h5>${user.username}</h5>
     <h5>Tickets : </h5>
    <#if tickets??>
        <#list tickets as ticket>
         <div class="container">
             <table style="width:100%">
                 <tr>
                     <th>Name</th>
                     <th>Showroom</th>
                     <th>Description</th>
                     <th>Price</th>
                     <th>Date</th>
                 </tr>
                 <hr/>

                 <tr>
                     <td>${ticket.id}</td>
                     <td>${ticket.showroom}</td>
                     <td style="max-width: 200px">${ticket.description}</td>
                     <td></td>
                     <td>${ticket.price}</td>
                     <td>${ticket.date}</td>
                 </tr>
             </table>
         </div>
         </#list>
     </#if>

</@p.page>