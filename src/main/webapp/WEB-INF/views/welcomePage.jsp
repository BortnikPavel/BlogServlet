<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Blog</title>
<link href="../../CSS/stylesheet.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="top_bar_black">
    <div id="logo_container">
        <div id="logo_image">
        </div>
        <div id="nav_block">
            <a class="nav_button" href="/welcomePage">Home</a>
            <a class="nav_button" href="/topic">Topics</a>
            <c:if test="${sessionScope.user.firstName==null}">
                <a class="nav_button" href="/login">Login</a>
                <a class="nav_button" href="/registration">Registration</a>
            </c:if>
            <c:if test="${sessionScope.user.firstName!=null}">
                <a class="nav_button" href="/logout">Logout</a>
                <a class="nav_button" href="/">Your page</a>
            </c:if>
	    </div>
    </div>
</div>
            <div id="content_container">
       	        <div id="header">
                    <div class="header_content_mainline">
                        Добро пожаловать в наш блог)
                    </div>
                        <div id="header_content_tagline">
                            Какой-то приветственный текст!
                        </div>
                    </div>
           
                    <div id="header_lower">
                         <div id="header_content_boxline">О нашем блоге
                             <div id="header_content_boxcontent">
                                 Портал для педагогов «Учсовет» создан для публикации статей,
                                 научных работ, планов и конспектов уроков, методических разработок,
                                 портфолио, конспектов занятий по ФГОС, сценариев для праздничных мероприятий.
                                 Целью нашего интернет-издания является обмен опытом учителей и педагогов со всего мира.
                                 На образовательном портале «Учсовет», сразу после публикации материала,
                                 вы сможете получить официальное свидетельство о публикации с уникальным регистрационным номером,
                                 заверенное печатью и подписью. На нашем портале, кроме публикации материалов,
                                 имеется возможность участия в уникальных Всероссийских конкурсных мероприятиях не только онлайн, но и очно.
                                 Редакция образовательного портала «Учсовет» состоит, исключительно,
                                 из профессионалов своего дела с большим опытом работы и высокой квалификацией.
                                 Мы поможем вам в реализации требований ФГОС, аттестации и публикации в СМИ.
                                 Надеемся, вам у нас понравится, и вы посетите наш портал снова. Расскажите о нас своим коллегам и друзьям.
                                 На образовательном портале «Учсовет» мы всегда рады вас видеть. Ведь мы работаем для вас, дорогие наши педагоги!
                             </div>
                         </div>
                    </div>
            </div>

<div id="bottom_bar_black">
    <div id="main_container">
	    <div id="header_lower">
            <div id="header_content_lowerline">Contact
	            <div id="header_content_lowerboxcontent">
	                Pavel<br />
	                Bortnik<br />
                    bortnikpp@gmail.com<br />
                    bortnik.p<br />
                    01982 698 621<BR />
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
