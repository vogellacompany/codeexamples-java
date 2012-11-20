<#include "./common/header.ftl"> 
<#if container??>
  <div ="${container}">
<#else>
  <div ="default">
</#if>

<ul>
<#list systems as system>
<li>${system_index + 1}.${system.getName()}</li>
</#list>
</ul>

</div>

<#include "./common/footer.ftl">  
