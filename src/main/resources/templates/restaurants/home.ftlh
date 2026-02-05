<#import "../layout.ftlh" as home>
<@home.layout>

    <div class="container">

    <div class="mb-4">


        <div class="d-flex justify-content-center mb-3">
            <form action="/restaurants" method="get" class="d-flex w-100" style="max-width: 500px;">
                <input type="text"
                       name="name"
                       class="form-control me-2  shadow-sm border-success"
                       placeholder="Поиск по названию ресторана..."
                       value="${(param.name)!''}"
                       aria-label="Поиск ресторана">
                <button type="submit" class="btn btn-success  px-4">
                    Найти
                </button>
            </form>
        </div>


        <#if restaurants?has_content && totalPages gt 1>
            <div class="d-flex justify-content-center">
                <nav aria-label="Page navigation">
                    <ul class="pagination pagination-lg mb-0">
                        <li class="page-item <#if currentPage == 0>disabled</#if>">
                            <a class="page-link text-purple border-purple"
                               href="/restaurants?page=${currentPage - 1}&sort=${sort!}">
                                ←
                            </a>
                        </li>
                        <#list 0..totalPages-1 as p>
                            <li class="page-item <#if p == currentPage>active</#if>">
                                <a class="page-link
                                        <#if p == currentPage>bg-purple text-white border-purple
                                        <#else>text-purple border-purple</#if>"
                                   href="/restaurants?page=${p}&sort=${sort!}">
                                    ${p + 1}
                                </a>
                            </li>
                        </#list>
                        <li class="page-item <#if currentPage + 1 == totalPages>disabled</#if>">
                            <a class="page-link text-purple border-purple"
                               href="/restaurants?page=${currentPage + 1}&sort=${sort!}">
                                →
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </#if>

    </div>

    <#if restaurants?has_content>
        <div class="row">
            <#list restaurants as r>
                <div class="col-12 mb-4">
                    <div class="card h-100 shadow border-purple">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title text-purple">${r.name}</h5>
                            <p class="card-text text-secondary">${r.description}</p>
                            <div class="mt-auto text-end">
                                <a href="/restaurants/${r.id}" class="btn btn-outline-success">Подробнее</a>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>

    <#else>
        <p class="text-muted text-center fs-5 py-5">Нет доступных заведений</p>
    </#if>

</@home.layout>