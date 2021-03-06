<%-- 
    Document   : UpdateRequest
    Created on : Feb 11, 2022, 11:26:59 PM
    Author     : Duc Minh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/WEB-INF/tlds/customTag" prefix="i" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
            crossorigin="anonymous"
            />
        <!-- Link to css file -->
        <link rel="stylesheet" href="<i:ReadUrlFromContext url="/assets/css/style.css"/>"/>
        <link rel="stylesheet" href="<i:ReadUrlFromContext url="/assets/css/RequestMain.css" />">
        <link rel="stylesheet" href="<i:ReadUrlFromContext url="/assets/css/CreateRequest.css" />">
        <link rel="stylesheet" href="<i:ReadUrlFromContext url="/assets/css/StarRating.css"/>"/>  

        <script src="https://kit.fontawesome.com/bc95aa25ab.js" crossorigin="anonymous"></script>
        <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
        <title>Update Request</title>
    </head>
    <body>
        <!-- Include header of web site from general-->
        <jsp:include page="./header.jsp"></jsp:include>
            <!--Main Content--> 
            <div id="wrapper" class="d-flex">
                <!--Side Bar  -->
                <nav class="sidebar close">
                    <!-- Side Bar header-->
                    <header>
                        <div class="image-text">
                            <span class="image">
                                <img src="<i:ReadUrlFromContext url="/assets/image/259151762_463419805435533_5232999905401287810_n.png" />" alt="">
                        </span>

                        <div class="text logo-text">
                            <span class="name">TutorDuo</span>
                            <span class="profession">Online Learning</span>
                        </div>
                    </div>
                    <i class='bx bx-chevron-right toggle'></i>
                </header>

                <!-- Main Menu of Side Bar-->
                <div class="menu-bar">
                    <div class="menu">
                        <ul class="menu-links">

                            <!-- This link for list all teacher table-->
                            <li class="nav-link">
                                <a href="ListAllTeacher">
                                    <i class='bx bx-table icon' ></i>
                                    <span class="text nav-text">Teacher Ranking</span>
                                </a>
                            </li>
                            <li class="nav-link">
                                <a href="ListAllRequest">
                                    <i class='bx bx-table icon' ></i>
                                    <span class="text nav-text">My Request</span>
                                </a>
                            </li>

                        </ul>
                    </div>

                    <!-- Dark/light mode-->
                    <div class="bottom-content">
                        <li class="mode">
                            <div class="sun-moon">
                                <i class='bx bx-moon icon moon'></i>
                                <i class='bx bx-sun icon sun'></i>
                            </div>
                            <span class="mode-text text">Dark mode</span>

                            <div class="toggle-switch">
                                <span class="switch"></span>
                            </div>
                        </li>
                    </div>
                </div>
            </nav>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">
                <!-- Main Content -->
                <div id="content">
                    <!-- Begin Page Content -->
                    <div class="container-fluid">
                        <!-- Page Heading -->
                        <div class="card--top"></div>
                        <h1>Request</h1>
                        <p>The request in our system contains exercise has sent by Student to Teacher in website.</p>
                        <div class="card--top--menu d-flex" id="cardTop">
                            <h3>Update Request</h3>
                        </div>
                        <div class="backBtn-container">
                            <a class="backBtn" href="ViewRequestStu?requestId=${requestScope.request.getSubjectID()}"
                               ><i class="fas fa-arrow-left"></i>&nbsp;&nbsp;<span>Back</span></a
                            >
                        </div>
                        <hr />
                        <!-- Create Container -->
                        <div class="create-container">
                            <!-- Request Info -->
                            <div class="createContent mb-4">
                                <form action="UpdateRequest" method="post" id="updateFrom">
                                    <div class="card p-2 mb-4">
                                        <div class="card-body">
                                            <h5 class="text-hightlight1 mb-32px">1. Infomation</h5>
                                            <input type="hidden" id="rr" name="rqId" readonly  value="${requestScope.request.getRequestID()}">
                                            <label for="reTitle" class="d-block">Request Title: </label>
                                            <input type="text" id="reTitle" name="rqTitle" class="form-control" value="${requestScope.request.getTitle()}" /><br />
                                            <div class="row">
                                                <!--Subject Section-->          
                                                <div class="form-group col-sm-6">
                                                    <label for="reSubject">Subject: </label>

                                                    <select class="form-control" id="rqSubject" name="rqSubject">
                                                        <option value="${requestScope.request.getSubjectID()}">${requestScope.subjectNames.get(requestScope.request.getSubjectID())}</option>
                                                        <c:forEach items="${requestScope.subjects}" var ="s" varStatus="loop">    
                                                            <option value="${s.getSubjectID()}">${s.getSubjectName()}</option>
                                                        </c:forEach>  
                                                    </select>
                                                </div>
                                                <!--Level Section-->          
                                                <div class="form-group col-sm-6">
                                                    <label for="reClass">Grade: </label>
                                                    <select class="form-control" id="reClass" name="rqLevel">
                                                        <option value="${requestScope.request.getLevel()}">${requestScope.request.getLevel()}</option>
                                                        <option value="10">Grade 10</option>
                                                        <option value="11">Grade 11</option>
                                                        <option value="12">Grade 12</option>
                                                    </select>
                                                </div>
                                                <!--Price Section-->            
                                                <div class="form-group col-sm-6">
                                                    <label for="rePrice">Price: </label>
                                                    <select class="form-control" id="rePrice" name="rqPrice">
                                                        <option value="${requestScope.request.getCost()}">${requestScope.request.getCost()}</option>
                                                        <option value="5000">5.000 <span>VND</span></option>
                                                        <option value="10000">10.000 <span>VND</span></option>
                                                        <option value="15000">15.000 <span>VND</span></option>
                                                        <option value="20000">20.000 <span>VND</span></option>
                                                        <option value="25000">25.000 <span>VND</span></option>
                                                        <option value="30000">30.000 <span>VND</span></option>
                                                        <option value="40000">40.000 <span>VND</span></option>
                                                        <option value="50000">50.000 <span>VND</span></option>
                                                    </select>
                                                </div>
                                            </div>
                                            <br />

                                            <!--Teacher Recommend-->      
                                            <fieldset class="mr-5 mb-4 form-group">
                                                <legend class="text-bold2 mb-12px h6">Teacher Recommend: </legend>
                                                <a style="text-decoration: none;display: inline-block" href="#" class="teacherRecommend--name">${requestScope.request.getTutorGet()}</a>
                                                <div class="offline-box flex-box align-items-start">
                                                    <p>Do you wanna change ?</p>
                                                    <div class="mr-5 form-check">
                                                        <label class="mb-0 cursor-pointer text-nowrap false"
                                                               ><input
                                                                name="teacherRcmCheck"
                                                                type="radio"
                                                                class="form-check-input"
                                                                checked=""
                                                                value="0"
                                                                />None</label
                                                        >
                                                    </div>
                                                    <div class="form-check">
                                                        <label class="mb-12px cursor-pointer text-grey"
                                                               ><input
                                                                name="teacherRcmCheck"
                                                                type="radio"
                                                                class="form-check-input teacherOption"
                                                                value="1"
                                                                />Yes</label
                                                        >

                                                        <input type="hidden" id="teacherUsernameRcm" name="rqTeacherRcm" value="${requestScope.request.getTutorGet()}" readonly >
                                                        <input type="hidden" id="studentSent" name="studentSent" value=${sessionScope.user.getUsername()}>  
                                                    </div>
                                                    <button
                                                        type="button"
                                                        class="btn btn-primary mt-3 teacherRecommend--btn"
                                                        data-toggle="modal"
                                                        data-target="#teacherListModal"
                                                        >
                                                        Find Teacher...
                                                    </button>
                                                </div>

                                            </fieldset>
                                        </div>
                                    </div>

                                    <!--Content Section-->                
                                    <div class="card shadow p-2">
                                        <div class="card-body">
                                            <h5 class="text-hightlight1 mb-32px">2. Content</h5>
                                            <div class="responseContent--text mt-3">
                                                <label for="TopicTextArea">Topic</label>
                                                <textarea class="TopicTextAre" name="content" style="width: 100%" id="message" minlength="10" maxlength="1000" type="text" rows="5" cols="30" dir="ltr" required>${requestScope.request.getContent()}</textarea>
                                            </div>
                                            <label for="imgLink" class="form-label"> Image attachment</label>
                                            <input class="form-control" type="file" id="imgLink" name="imgContent" value="${requestScope.request.getImageLink()}"/>
                                        </div>
                                    </div>
                                    <input type="submit" class="btn btn-primary mt-3" value="ReupLoad Request" />
                                </form>

                            </div>
                        </div>
                    </div>
                </div>

                <!-- End of Content Wrapper -->
            </div>

            <!-- End of Page Wrapper -->
        </div>

        <!-- Footer -->
        <%@include file="./footer.jsp" %>
        <!-- End of Footer -->

        <!-- Modal Image -->
        <div id="myModal" class="modal--Img">
            <span class="close--Img">&times;</span>
            <img class="modal-content--Img" id="img01" />
            <div id="caption"></div>
        </div>
        <!-- Modal Teacher List -->
        <div
            class="modal fade"
            id="teacherListModal"
            tabindex="-1"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
            >
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header float-right">
                        <h4 class="text-center">Teacher Recommend</h4>
                        <div class="text-right">
                            <i data-dismiss="modal" aria-label="Close" class="fa fa-close"></i>
                        </div>
                    </div>
                    <div class="modal-body">
                        <div class="row mt-3 mb-3">
                            <div class="col-sm-12 col-md-6">
                                <h5 mt-3>Teacher: <span id="teacherRcmName"></span></h5>
                            </div>
                            <div class="col-sm-12 col-md-6">
                                <div id="dataTable_filter" class="dataTables_filter">
                                    <label
                                        >Search:<input
                                            type="search"
                                            class="form-control form-control-sm"
                                            id ="searchString"
                                            placeholder=""
                                            aria-controls="dataTable"
                                            /></label>
                                </div>
                            </div>
                        </div>
                        <div class="table-responsive">
                            <table
                                class="table table-hover tableTeacher"
                                id="dataTable"
                                width="100%"
                                cellspacing="0"
                                >
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>Name</th>
                                        <th>Reputation</th>
                                        <th>Subject</th>
                                        <th></th>
                                        <th>Request</th>
                                    </tr>
                                </thead>
                                <tbody id="tbBody">
                                    <c:forEach items="${requestScope.teachers}" var ="t" varStatus="loop"> 
                                        <tr>
                                            <td>${loop.index+1}</td>
                                            <td>
                                                <a class="teacherInfo" href="#" id="teacherInfo" 
                                                   ><img
                                                        class="teacherAvt"
                                                        src="<i:ReadUrlFromContext url="${t.getAvatar()}"/>"
                                                        alt=""
                                                        />${t.getDisplayName()}</a
                                                >
                                            </td>
                                            <td>
                                                <div id="rating">
                                                    <input type="radio" id="star5${loop.index+1}" name="rating${loop.index+1}" value="5" disabled/>
                                                    <label class="full" for="star5${loop.index+1}" title="Awesome - 5 stars"></label>

                                                    <input type="radio" id="star4half${loop.index+1}" name="rating${loop.index+1}" value="4 and a half" disabled/>
                                                    <label class="half" for="star4half${loop.index+1}" title="Pretty good - 4.5 stars"></label>

                                                    <input type="radio" id="star4${loop.index+1}" name="rating${loop.index+1}" value="4" disabled/>
                                                    <label class="full" for="star4${loop.index+1}" title="Pretty good - 4 stars"></label>

                                                    <input type="radio" id="star3half${loop.index+1}" name="rating${loop.index+1}" value="3 and a half" disabled/>
                                                    <label class="half" for="star3half${loop.index+1}" title="Meh - 3.5 stars"></label>

                                                    <input type="radio" id="star3${loop.index+1}" name="rating${loop.index+1}" value="3" disabled/>
                                                    <label class="full" for="star3${loop.index+1}" title="Meh - 3 stars"></label>

                                                    <input type="radio" id="star2half${loop.index+1}" name="rating${loop.index+1}" value="2 and a half" disabled/>
                                                    <label class="half" for="star2half${loop.index+1}" title="Kinda bad - 2.5 stars"></label>

                                                    <input type="radio" id="star2${loop.index+1}" name="rating${loop.index+1}" value="2" disabled/>
                                                    <label class="full" for="star2${loop.index+1}" title="Kinda bad - 2 stars"></label>

                                                    <input type="radio" id="star1half${loop.index+1}" name="rating${loop.index+1}" value="1 and a half" disabled/>
                                                    <label class="half" for="star1half${loop.index+1}" title="Meh - 1.5 stars"></label>

                                                    <input type="radio" id="star1${loop.index+1}" name="rating${loop.index+1}" value="1" disabled/>
                                                    <label class="full" for="star1${loop.index+1}" title="Sucks big time - 1 star"></label>

                                                    <input type="radio" id="starhalf${loop.index+1}" name="rating${loop.index+1}" value="half" disabled/>
                                                    <label class="half" for="starhalf${loop.index+1}" title="Sucks big time - 0.5 stars"></label>
                                                </div>
                                            </td>
                                            <td>${requestScope.subjectNames.get(t.getSubjectId())}</td>
                                            <td>
                                                <input type="hidden" id="teacherUsername" value="${t.getUsername()}"> 
                                            </td>
                                            <td>
                                                <button
                                                    type="button"
                                                    class="btn btn-primary requestBtn"
                                                    value="${t.getUsername()}"
                                                    aria-label="Close"
                                                    id="closemodal"
                                                    data="${t.getDisplayName()}"
                                                    >
                                                    Select
                                                </button>
                                            </td>
                                        </tr>

                                    </c:forEach> 
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <button type="button savebtn" class="btn btn-primary saveBtn mb-4" data-dismiss="modal">
                        Save
                    </button>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        

        <!-- link to java script file -->
        <script src="<i:ReadUrlFromContext url="/assets/js/CheckValidatorRequest.js"/>"></script>
        <script src="<i:ReadUrlFromContext url="/assets/js/RequestMain.js"/>"></script>

        <script>
$(document).ready(function () {
    $(".form-check-input").each(function () {
        $(this).on("click", function () {
            var rcmDecision = ($(this).val());
            if (rcmDecision === "1") {
                yesOption();
            } else if (rcmDecision === "0")
                noOption();
        });

    });
    function yesOption() {
        $(".teacherRecommend--btn").css("display", "block");
        $(".teacherRecommend--name").css("display", "inline");
        selectTeacher();
    }
    function noOption() {
        $(".teacherRecommend--btn").css("display", "none");
        $(".teacherRecommend--name").css("display", "none");
        $("#teacherUsernameRcm").val(null);
    }
    function selectTeacher() {
        $("#dataTable .requestBtn").click(function () {
            $('#dataTable .requestBtn.select').not(this).removeClass('select');
            $(this).toggleClass('select');
            var teacherUsername = ($(this).val());
            $("#teacherUsernameRcm").val(teacherUsername);
            $(".teacherRecommend--name").html(teacherUsername);
            $("#teacherRcmName").html($(this).attr("data"))
        });
    }
    function checkTeacher() {
        $('#tbBody tr .requestBtn').each(function () {
            if ($(this).val() === $("#teacherUsernameRcm").val()) {
                $(this).toggleClass("select");
            }
        });
    }
    $("#searchString").on('keyup', function () {
        var searchString = $(this).val();
        var subjectId = $("#rqSubject option:selected").val();
        $.ajax({
            url: "TeacherRecommendSearch",
            data: {"searchString": searchString,
                "subjectId": subjectId},
            type: "POST",
            success: function (result)
            {
                console.log(result);
                $('#dataTable #tbBody').empty();
                $('#dataTable #tbBody').append(result);
                checkTeacher();
                selectTeacher();
                <c:forEach items="${requestScope.teachers}" var ="t" varStatus="loop">
                    window.document.onload = calcRate(${t.getReputation()}, ${loop.index+1});
                </c:forEach>
            }
        });
    });
});
            <c:forEach items="${requestScope.teachers}" var ="t" varStatus="loop">
            window.document.onload = calcRate(${t.getReputation()}, ${loop.index+1});
            </c:forEach>

            function calcRate(r, num) {
                const f = ~~r, // = Math.floor(r)
                        id = "star" + f + (r % f ? "half" + num : "" + num);
                id && (document.getElementById(id).checked = !0);
            }

        </script>  
        <script>
            // Onload event
            $(document).ready(function () {
                $("#cardTop")[0].scrollIntoView({
                    behavior: "smooth", // or "auto" or "instant"
                    block: "start" // or "end"
                });
            });

        </script>
    </body>
</html>
