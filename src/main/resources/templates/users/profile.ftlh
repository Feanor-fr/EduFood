<#import "../layout.ftlh" as main>
<@main.layout>

    <section class="py-5">
        <div class="container">

            <h2 class="text-center fw-bold mb-5">Личный кабинет</h2>

            <div class="rounded-4 p-4 p-md-5 bg-white border border-purple-subtle shadow-sm">


                <div class="row mb-5 g-4 align-items-center">

                    <div class="col-lg-8">
                        <h4 class="fw-bold mb-2">
                            <#if user.name?? && user.name?has_content>
                                ${user.name}
                            <#else>
                                Пользователь
                            </#if>
                        </h4>
                        <p class="text-muted mb-2"><strong>Email:</strong> ${user.email}</p>

                    </div>

                </div>


                <h4 class="fw-bold mb-4 text-center text-md-start">История заказов</h4>


                <nav aria-label="Page navigation" class="mt-4 mb-5">
                    <ul class="pagination mb-0 justify-content-center">

                        <li class="page-item <#if currentPage == 0>disabled</#if>">
                            <a class="page-link text-purple border-purple
                                     <#if currentPage == 0>bg-light text-muted border-muted
                                     <#else>bg-white hover-bg-light</#if>"
                               href="?page=${currentPage - 1}">
                                ←
                            </a>
                        </li>

                        <#list 0..totalPages-1 as p>
                            <li class="page-item <#if p == currentPage>active</#if>">
                                <a class="page-link
                                         <#if p == currentPage>
                                             bg-purple text-white border-purple
                                         <#else>
                                             bg-white text-purple border-purple hover-bg-light
                                         </#if>"
                                   href="?page=${p}">
                                    ${p + 1}
                                </a>
                            </li>
                        </#list>

                        <li class="page-item <#if currentPage + 1 == totalPages>disabled</#if>">
                            <a class="page-link text-purple border-purple
                                     <#if currentPage + 1 == totalPages>bg-light text-muted border-muted
                                     <#else>bg-white hover-bg-light</#if>"
                               href="?page=${currentPage + 1}">
                                →
                            </a>
                        </li>

                    </ul>
                </nav>


                <#if orders?? && orders?size gt 0>
                    <#list orders as o>
                        <div class="card mb-4 shadow-sm border-purple border-1 rounded-3">
                            <div class="card-body p-4">
                                <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center">
                                    <div>
                                        <p class="text-muted mb-2">
                                            <strong>Дата:</strong>
                                            ${o.createdDate}
                                        </p>
                                         <p class="text-dark fw-bold mb-0">
                                            Сумма: ${o.totalSum?string("0.##")} сом
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#list>
                <#else>
                    <div class="p-5 text-center rounded-3 bg-light">
                        <p class="text-muted fs-5 mb-2">У вас пока нет заказов</p>
                        <p class="text-secondary small">
                            Закажите что-нибудь вкусное и история появится здесь
                        </p>
                    </div>
                </#if>

            </div>
        </div>
    </section>

</@main.layout>