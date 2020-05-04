# js 网络请求方式

## XMLHttpRequest-ajax
* 后端接口 /login ，采用 post 方式，Form 表单方式提交

    ```html
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="utf-8">
        <title>登录页面</title>
    </head>
    <body>
    <p>登录页面</p>

    <div id="container">
        <input type="text" placeholder="用户名" id="username">
        <input type="text" placeholder="密码" id="password">
        <button type="button" onclick="login()">登录</button>
    </div>
    <script type="text/javascript">
        function login() {
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    var result = xmlhttp.responseText;
                    var obj = JSON.parse(result);
                    if (obj.code == 0) {
                        alert(obj.message);
                    } else {
                        alert("登录失败原因：" + obj.message);
                    }
                }
            }
            xmlhttp.open("POST", "http://localhost:8080/login", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("username=Bill&password=123");
        }
    </script>
    </body>
    </html>
    ```

## jQuery-ajax
* 后端接口采用一样的
    ```html
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="utf-8">
        <title>登录页面-jQuery</title>
        <script src="js/jquery-3.5.0.js"></script>
    </head>
    <body>
    <p>登录页面-jQuery</p>

    <div id="container">
        <input type="text" placeholder="用户名" id="username">
        <input type="text" placeholder="密码" id="password">
        <button id="loginBtn">登录</button>
        <button onclick="loginBtn2()">登录</button>
    </div>
    <script>
        $(document).ready(function () {
            $("#loginBtn").click(function () {
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8087/login",
                    data: "username=Bill&password=Gates",
                    async: true,
                    cache: false,
                    contentType: "application/x-www-form-urlencoded",
                    processData: false,
                    success: function (result) {
                        alert(result.message);
                    },
                    error: function (XMLHttpRequest, result) {
                        alert(result.message);
                    }
                })
            })
        });
        function loginBtn2() {
            $.ajax({
                type: "POST",
                url: "http://localhost:8087/login",
                data: "username=Bill&password=Gates",
                async: true,
                cache: false,
                contentType: "application/x-www-form-urlencoded",
                processData: false,
                success: function (result) {
                    alert(result.message);
                },
                error: function (XMLHttpRequest, result) {
                    alert(result.message);
                }
            })
        };
    </script>
    </body>
    </html>
    ```
## axios
* 后端接口采用一样的
    ```html
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="utf-8">
        <title>登录页面-axios</title>
        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    </head>
    <body>
    <p>登录页面-axios</p>

    <div id="container">
        <input type="text" placeholder="用户名" id="username">
        <input type="text" placeholder="密码" id="password">
        <button onclick="loginBtn()">登录</button>
    </div>
    <script>
        function loginBtn() {
            axios({
                url: "http://localhost:8087/login",
                method: "post",
                data: "username=Bill&password=Gates",

            }).then(function (response) {
                if (response !== null && response.status === 200 && response.data.code === 0) {
                    alert(response.data.message);
                } else {
                    alert("登录失败");
                    console.log(response.data.message);
                }
            }).catch(function (error) {
                console.log(error);
            })

        }
    </script>
    </body>
    </html>
    ```

## 接口信息
```json
POST /login HTTP/1.1
Host: localhost:8087
Content-Type: application/x-www-form-urlencoded

username=bill&password=123
```


