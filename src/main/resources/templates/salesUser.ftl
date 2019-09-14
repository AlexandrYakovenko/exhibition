<#import "parts/page.ftl" as p>

<@p.page>
     <h5>${user.username}</h5>
     <h5>Tickets : </h5>

     <#if tickets??>
         <#list tickets as ticket>
             <table style="width:100%">
                 <tr>
                     <th>Name</th>
                     <th>Showroom</th>
                     <th>Description</th>
                     <th>Author</th>
                     <th>Price</th>
                     <th>Date</th>
                 </tr>
                 <tr>
                     <td>${ticket.id}</td>
                     <td>${ticket.showroom}</td>
                     <td><#--${ticket.description}--></td>
                     <td></td>
                     <#--<td>${ticket.author}</td> НА ЄТОй СТРОЧКЕ ВСЕЛЕТИТ К ХУЯМ-->
                     <td>Price</td>
                     <td>Date</td>
                 </tr>
             </table>
         </#list>
     </#if>

</@p.page>