<html>
<head>
    <title>Code</title>
</head>
<body>
    <#list allCodes as code>
        <span id="load_date">${code.date}</span>
        <pre id="code_snippet">${code.code}</pre>
    </#list>
</body>
</html>