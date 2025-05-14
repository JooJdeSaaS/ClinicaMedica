<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Sucesso</title>
    <script>
        window.onload = function() {
            alert("${sujeito} ${verbo} com sucesso!");
            window.location.href = "<%= request.getAttribute("redirect") %>";
        };
    </script>
</head>
<body>
</body>
</html>
