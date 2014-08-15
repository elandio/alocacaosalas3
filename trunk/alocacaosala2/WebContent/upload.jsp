<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exemplo de Upload</title>
    </head>
    <body>
        
    <center>
        <h1>Selecione o arquivo abaixo e realize o upload</h1>
        
        <form method="POST" action="upload" enctype="multipart/form-data">
        <table border="1">
            <tr>
                <td>Arquivo</td>
                <td><input type="file" name="file"></td>
            </tr>
        </table>
            <input type="submit" value="salvar">
        </form>
    </center>
    </body>
</html>
