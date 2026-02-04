<#import "../layout.ftlh" as main>
<@main.layout>

    <section class="py-5">
        <div class="container">

            <h2 class="text-center fw-bold mb-5">Ресторан</h2>

            <div class="rounded-4 p-4 p-md-5">

                <div class="row mb-5 g-4 align-items-start">

                    <div class="col-lg-8">
                        <h4 class="fw-bold mb-2">${restaurant.name}</h4>
                        <#if restaurant.description??>
                            <p class="text-muted mb-3">${restaurant.description}</p>
                        </#if>
                    </div>

                </div>

                <h4 class="fw-bold mb-4 text-center text-md-start">Блюда</h4>

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

                <#if dishes?? && dishes?size gt 0>
                    <#list dishes as dish>
                        <div class="card mb-4 shadow-sm border-2 rounded-3">
                            <div class="card-body p-4 d-flex flex-column flex-md-row align-items-md-center justify-content-md-between">
                                <div>
                                    <h5 class="fw-medium mb-3">${dish.name!'Название блюда'}</h5>
                                    <p class="text-muted mb-0"><strong>Цена:</strong> ${dish.price} сом</p>
                                </div>
                                <div class="mt-3 mt-md-0">
                                    <a href="#" class="btn btn-outline-purple btn-sm rounded-pill">Заказать</a>
                                </div>
                            </div>
                        </div>
                    </#list>
                <#else>
                    <div class="p-5 text-center rounded-3">
                        <p class="text-muted fs-5 mb-0">В ресторане пока нет блюд</p>
                    </div>
                </#if>

            </div>
        </div>
    </section>

</@main.layout>