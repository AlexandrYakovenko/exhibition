<#import "parts/page.ftl" as p>

<@p.page>
<h5>${user.username}</h5>
<h5>Balance : ${user.accountMoney}</h5>

<div class="form-group mt-3">
    <div class="col-sm-6">
        <form action="/sales/addMoney/${user.id}" method="post">
            <!-- Add money -->
            <div class="form-group">
                <label for="exhibition">Replenish balance</label>
                <input type="text" name="accountMoney" class="form-control ${(moneyError??)?string('is-invalid', '')}"
               <#-- pattern="[1-9]{1}|^[1-9]{1}[0-9]{1}|^[1-9]{1}[0-9]{1}[0-9]{1}|^1000"-->
                       id="exhibition"  placeholder="0"/>
                        <#if moneyError??>
                            <div class="invalid-feedback">
                                ${moneyError}
                            </div>
                        </#if>

                <!-- security_token only to post-methods -->
                <input type="hidden" name="_csrf" value="${_csrf.token}" />

                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            </div>
        </form>
    </div>
</div>

</@p.page>