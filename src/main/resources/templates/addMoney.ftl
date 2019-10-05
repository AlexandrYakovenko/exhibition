<#import "parts/page.ftl" as p>

<@p.page>

    <h5><@spring.message "label.username"/>${user.username}</h5>

    <h5><@spring.message "label.balance"/>${user.accountMoney}</h5>

    <div class="form-group mt-3">
        <div class="col-sm-4">
            <form action="/sales/addMoney/${user.id}" method="post">
                <!-- Add money -->
                <div class="form-group">
                    <label for="exhibition">
                        <@spring.message "label.replenish_balance"/>
                    </label>
                    <input type="text" name="accountMoney" class="form-control ${(moneyError??)?string('is-invalid', '')}"
                           pattern="[1-9]{1}|^[1-9]{1}[0-9]{1}|^[1-9]{1}[0-9]{1}[0-9]{1}|^1000"
                           id="exhibition"  placeholder="0"/>
                            <#if moneyError??>
                                <div class="invalid-feedback">
                                    ${moneyError}
                                </div>
                            </#if>

                    <!-- security_token only to post-methods -->
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary mt-3">
                            <@spring.message "button.add"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</@p.page>