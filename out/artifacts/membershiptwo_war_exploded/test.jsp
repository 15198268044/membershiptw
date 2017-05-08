<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>File Upload with Jersey</h1>
<!--/web/user/uploadFile.do-->
<form action="/fileupload" method="post" enctype="multipart/form-data">
    <p>
        <input type="hidden" name="userId" value="3"  />
        Select a file : <input type="file" name="file" size="45" />
    </p>

    <input type="submit" value="Upload It" />
</form>
</body>
</html>
