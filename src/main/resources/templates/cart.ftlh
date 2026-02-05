<#import "layout.ftlh" as main>
<@main.layout>

    <h2 class="mb-4">Корзина</h2>

    <#if items?size == 0>
        <p>Корзина пуста</p>
    <#else>

        <table class="table">
            <thead>
            <tr>
                <th>Ресторан</th>
                <th>Блюдо</th>
                <th>Цена</th>
                <th>Кол-во</th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <#list items as item>
                <tr>
                    <td>${item.dish.restaurantName}</td>
                    <td>${item.dish.name}</td>
                    <td>${item.dish.price}</td>
                    <td>${item.quantity}</td>
                    <td>
                        <form method="post" action="/cart/remove">
                            <input type="hidden" name="dishId" value="${item.dish.id}">
                            <button class="btn btn-sm btn-purple">Удалить</button>
                            <#if _csrf??>
                                <input
                                        type="hidden"
                                        name="${(_csrf.parameterName)!'csrf-param-name'}"
                                        value="${(_csrf.token)!'csrf-token'}"
                                >
                            </#if>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>

    </#if>

    <#if items?size gt 0>
        <h4 class="text-end">Итого: <strong>${total}</strong> сом</h4>

        <div class="d-flex justify-content-end mt-4">
            <#assign isAuth = SPRING_SECURITY_CONTEXT??>

            <#if isAuth>
                <form action="/cart/checkout" method="post">
                    <button type="submit" class="btn btn-outline-purple  px-5">
                        Оформить заказ
                    </button>
                    <#if _csrf??>
                                <input
                                        type="hidden"
                                        name="${(_csrf.parameterName)!'csrf-param-name'}"
                                        value="${(_csrf.token)!'csrf-token'}">
                    </#if>
                </form>
            <#else>

            </#if>
        </div>
    </#if>

</@main.layout>
