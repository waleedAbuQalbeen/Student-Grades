<html>
<head>
<title>Login Page</title>
</head>
<body>
    <p><font color="red">${errorMessage}</font></p>
    <form action="/login" method="POST">
        ID : <input name="ID" type="text" /> Password : <input name="password" type="password" /> <input type="submit" />
    </form>
</body>
</html>