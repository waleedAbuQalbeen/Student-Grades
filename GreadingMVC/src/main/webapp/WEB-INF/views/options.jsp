<html>
<head>
<title>Login Page</title>
</head>
<body>
    <p><font color="red">${errorMessage}</font></p>
    <form action="/list-grades" method="POST">
         <input type="submit" value = "List all grades" />
    </form>
    </br></br>
    <form action="/list-information" method="POST">
            Course id : <input name="curs_id" type="text" />  <input type="submit" />
        </form>
        </br></br>

</body>
</html>