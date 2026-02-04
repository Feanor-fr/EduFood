<#import "../layout.ftlh" as home>
<@home.layout>

    <#if restaurants?has_content>

        <div class="d-flex align-items-center justify-content-between mb-4">
            <div style="width: 280px;"></div>

            <!-- Форма поиска -->
            <form action="/restaurants" method="get" class="d-flex w-100 w-md-50">
                <input type="text"
                       name="name"
                       class="form-control me-2"
                       placeholder="Поиск по названию ресторана..."
                       value="${(param.name)!''}"
                       aria-label="Поиск ресторана">
                <button type="submit" class="btn btn-success">Найти</button>
            </form>

            <#if totalPages != 1>
                <nav aria-label="Page navigation">
                    <ul class="pagination pagination-lg mb-0">
                        <li class="page-item <#if currentPage == 0>disabled</#if>">
                            <a class="page-link text-purple border-purple" href="/restaurants?page=${currentPage - 1}&sort=${sort!}">
                                ←
                            </a>
                        </li>
                        <#list 0..totalPages-1 as p>
                            <li class="page-item <#if p == currentPage>active</#if>">
                                <a class="page-link
                            <#if p == currentPage>bg-purple text-white
                            <#else>text-purple border-purple</#if>"
                                   href="/restaurants?page=${p}&sort=${sort!}">
                                    ${p + 1}
                                </a>
                            </li>
                        </#list>
                        <li class="page-item <#if currentPage + 1 == totalPages>disabled</#if>">
                            <a class="page-link text-purple border-purple" href="/restaurants?page=${currentPage + 1}&sort=${sort!}">
                                →
                            </a>
                        </li>
                    </ul>
                </nav>
            </#if>
        </div>

        <div class="row">
            <#list restaurants as r>
                <div class="col-12 mb-4">
                    <div class="card h-100 shadow border-purple">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title text-purple">${r.name}</h5>
                            <p class="card-text text-secondary">${r.description}</p>
                            <div class="mt-auto text-end">
                                <a href="#" class="btn btn-outline-success">Подробнее</a>
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