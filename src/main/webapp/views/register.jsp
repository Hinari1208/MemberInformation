<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会員情報登録</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h1>会員情報登録</h1>
    
    <!-- メッセージ表示部分 -->
    <c:if test="${not empty errorMessage}">
        <div style="color: red;">${errorMessage}</div>
    </c:if>
    <c:if test="${not empty successMessage}">
        <div style="color: green;">${successMessage}</div>
    </c:if>
    
    <form action="register" method="post">
        <div>
            <label for="loginId">ユーザID:</label>
            <input type="text" id="loginId" name="loginId" required>
        </div>
        <div>
            <label for="password">パスワード:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <label for="lastName">名前_姓:</label>
            <input type="text" id="lastName" name="lastName" required>
            <label for="firstName">名前_名:</label>
            <input type="text" id="firstName" name="firstName" required>
        </div>
        <div>
            <label for="sex">性別:</label>
            <input type="radio" id="male" name="sex" value="1" required> 男
            <input type="radio" id="female" name="sex" value="2" required> 女
        </div>
        <div>
            <label for="birthYear">生年月日:</label>
            <input type="text" id="birthYear" name="birthYear" placeholder="年" required>
            <input type="text" id="birthMonth" name="birthMonth" placeholder="月" required>
            <input type="text" id="birthDay" name="birthDay" placeholder="日" required>
        </div>
        <div>
            <label for="phoneNumber">電話番号:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" required>
        </div>
        <div>
            <label for="mailAddress">メールアドレス:</label>
            <input type="email" id="mailAddress" name="mailAddress" required>
        </div>
        <div>
            <label for="job">職業:</label>
            <select id="job" name="job" required>
                <option value="100">会社員</option>
                <option value="200">自営業</option>
                <option value="300">学生</option>
                <option value="400">その他</option>
            </select>
        </div>
        <div>
            <button type="submit">登録</button>
            <button type="reset">リセット</button>
            <button type="button" onclick="history.back()">戻る</button>
        </div>
    </form>
</body>
</html>
