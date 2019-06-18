<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#if Session.SPRING_SECURITY_CONTEXT.authentication.authenticated>
        <#assign
            user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        >
    </#if>
</#if>