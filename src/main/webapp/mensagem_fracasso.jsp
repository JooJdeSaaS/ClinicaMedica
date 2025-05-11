<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Sucesso</title>
    <script>
        window.onload = function() {
            alert("Ocorreu um erro ao ${texto}, por favor contate o suporte para resolver o problema");
            window.location.href = "<%= request.getAttribute("redirect") %>";
        };
    </script>
</head>
<body>
</body>
</html>
