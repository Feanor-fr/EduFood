<#import "../layout.ftlh" as reg>
<@reg.layout ; spring>

    <section class="py-5">
        <div class="card mx-auto shadow-lg border border-purple-subtle rounded-4 bg-white" style="max-width: 580px;">
            <div class="card-body py-5 px-4 px-md-5">

                <h3 class="mb-5 fw-bold text-center">Регистрация</h3>

                <form id="register-form" action="/auth/register" method="post">
                    <#if _csrf??>
                        <input
                                type="hidden"
                                name="${(_csrf.parameterName)!'csrf-param-name'}"
                                value="${(_csrf.token)!'csrf-token'}"
                        >
                    </#if>


                    <div class="mb-4">
                        <label for="email" class="form-label fw-medium">Email</label>
                        <@spring.formInput "userDto.email" 'id= "email" class="form-control border-purple"' />
                        <@spring.showErrors "<br>" "error text-danger" />
                    </div>

                    <div class="mb-4">
                        <label for="password" class="form-label fw-medium">Пароль</label>
                        <@spring.formPasswordInput "userDto.password" 'id= "password" type="password" class="form-control border-purple"' />
                        <@spring.showErrors "<br>" "error text-danger" />
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-purple btn-lg px-5 py-3 fw-bold">
                            Зарегистрироваться
                        </button>
                    </div>

                </form>

            </div>
        </div>
    </section>
</@reg.layout>