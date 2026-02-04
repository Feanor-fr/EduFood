<#import "spring.ftl" as spring>
<#macro layout>
    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="_csrf_header" content="${_csrf.headerName}">
        <meta name="_csrf_token" content="${_csrf.token}">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

        <style>
            :root {
                --purple: #6f42c1;
                --purple-dark: #5a35a3;
            }
            .text-purple {
                color: var(--purple) !important;
            }
            .border-purple {
                border-color: var(--purple) !important;
            }
            .bg-purple {
                background-color: var(--purple) !important;
            }
            .btn-purple {
                background-color: var(--purple);
                border-color: var(--purple);
                color: white;
            }
            .btn-purple:hover,
            .btn-purple:focus {
                background-color: var(--purple-dark);
                border-color: var(--purple-dark);
            }
            .btn-outline-purple {
                color: var(--purple);
                border-color: var(--purple);
            }
            .btn-outline-purple:hover,
            .btn-outline-purple:focus {
                background-color: var(--purple);
                color: white;
                border-color: var(--purple);
            }
            .page-link.bg-purple {
                background-color: var(--purple);
                border-color: var(--purple);
                color: white;
            }
        </style>

        <title>Edu Food</title>
    </head>
    <body class="d-flex flex-column min-vh-100 bg-white">

    <nav class="navbar navbar-expand-lg bg-success text-white shadow-sm">
        <div class="container-fluid px-3 px-md-4">
            <a class="navbar-brand fw-bold fs-4 text-white" href="/">Edu Food</a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarContent">
                <div class="ms-auto d-flex align-items-center gap-3">
                    <#assign known = SPRING_SECURITY_CONTEXT?? >
                    <#if known>
                        <#assign user = SPRING_SECURITY_CONTEXT.authentication.principal>
                        <#if user??>
                            <a href="/users/profile" class="text-white text-decoration-none fw-semibold me-3">
                                ${user.username}
                            </a>
                            <form action="/auth/logout" method="post" class="m-0">
                                <#if _csrf??>
                                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" >
                                </#if>
                                <button type="submit" class="btn btn-outline-purple px-4">Выйти</button>
                            </form>
                        </#if>
                    <#else>
                        <a href="#" class="btn btn-purple px-4">Корзина</a>
                        <a href="/auth/login"  class="btn btn-purple px-4 fw-semibold">Вход</a>
                        <a href="/auth/register" class="btn btn-purple px-4">Регистрация</a>
                    </#if>
                </div>
            </div>
        </div>
    </nav>

    <main class="container py-4 py-md-5 flex-grow-1">
        <#nested spring>
    </main>

    <footer class="mt-auto bg-success py-3">
        <div class="container text-center">
            <span class="text-white fw-medium">Edu Food © ${.now?string("yyyy")}</span>
        </div>
    </footer>

    </body>
    </html>
</#macro>