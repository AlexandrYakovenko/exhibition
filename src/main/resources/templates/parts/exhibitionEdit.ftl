
 <a class="btn btn-primary mt-3" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
     Exhibition editor
 </a>

    <div class="collapse <#if exhibition??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <div class="col-sm-6">
                <form action="/main" method="post">
                    <!-- Name of Exhibition -->
                    <div class="form-group">
                        <label for="exhibition">Exhibition</label>
                        <input type="text" name="name" class="form-control ${(nameError??)?string('is-invalid', '')}"
                               id="exhibition" value="<#if exhibition??>${exhibition.name}</#if>" placeholder="name of exhibition"/>
                        <#if nameError??>
                            <div class="invalid-feedback">
                                ${nameError}
                            </div>
                        </#if>
                    </div>

                    <!-- Showroom of Exhibition -->
                    <div class="form-group">
                        <label for="showroom">Showroom</label>
                        <input type="text" name="showroom" class="form-control ${(showroomError??)?string('is-invalid', '')}"
                               id="showroom" value="<#if exhibition??>${exhibition.showroom}</#if>" placeholder="name of showroom"/>
                         <#if showroomError??>
                                <div class="invalid-feedback">
                                    ${showroomError}
                                </div>
                         </#if>
                    </div>

                    <!-- Description of Exhibition -->
                    <div class="form-group">
                        <label for="description">Description</label>
                        <input type="text" name="description" class="form-control ${(descriptionError??)?string('is-invalid', '')}"
                               id="description" value="<#if exhibition??>${exhibition.description}</#if>" placeholder="description of event"/>
                        <#if descriptionError??>
                                    <div class="invalid-feedback">
                                        ${descriptionError}
                                    </div>
                        </#if>
                    </div>

                    <!-- security_token only to post-methods -->
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <input type="hidden" name="id" value="<#if exhibition??>${exhibition.id}</#if>" />
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>