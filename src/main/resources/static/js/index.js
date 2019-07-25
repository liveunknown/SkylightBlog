var articleSum;
//网站最后更新时间（版本更新需更改）
var siteLastUpdateTime = '2019年06月01日12点';

//网站开始时间
var siteBeginRunningTime = '2018-06-01 20:00:00';

$(function(){
    LoadPage(1);
    getArticleSum();
    getSiteInfoDetail();
});

function LoadPage(page) {
    $.ajax({
        url: "/articleInfoDetailsByCategoryId",
        type: "GET",
        async: false,
        data: {page:page},

        success: function (data) {
            var content = "";
            var labels = "";

            for (var i in data) {
                var id = data[i].articleInfoId;
                var createTime = data[i].createBy;
                var category = data[i].category.name;
                var title = data[i].title;
                var summary = data[i].summary;

                for(var j=0;j<data[i].articleLabelList.length;j++)
                {
                    labels = labels + '<span class="glyphicon glyphicon-paperclip"></span> ' + data[i].articleLabelList[j].label.name + '&#8194;';
                }

                content =
                    '<div class="col-xs-12 col-lg-12 shadow distance am-animation-slide-left">'+
                    '                    <h3>'+title+'</h3>'+
                    '                    <p>'+
                    '                    <span class="glyphicon glyphicon-time"></span>'+createTime+ '&#8197;'+
                    '                    <span class="glyphicon glyphicon-user"></span> 原创'+ '&#8197;'+
                    '                    <span class="glyphicon glyphicon-tags"></span> '+category+'</p>'+
                    '                    <p>'+ summary +'</p>'+
                    '                    <p><a class="btn btn-primary" href="/article.html?id='+ id + '" role="button">View details &raquo;</a></p>'+
                    '                    <hr>'+
                    '                    <p>'+ labels +'</p>'+
                    '                </div>';
                labels = "";

                $("#lastOne").append(content);
            }


            //$("#lastOne").html(content);

        }, error: function () {
            alert("数据加载错误");
        }

    });
}


function getArticleSum() {
    $.ajax({
        url: "/articleSum",
        type: "GET",
        async: false,
        data: {},
        success: function (data) {
            articleSum = data;
            console.log("文章总数为：" + articleSum);
            $("#sum").text(articleSum);
        }, error: function () {
            alert("数据加载错误");
        }
    });
}

function previousPage(){
    var page=$("#page").text();
    page=page-1;
    if(page<1){
        alert("已是第一页");
    }
    else{
        $("#lastOne").html("");
        LoadPage(page);
        $("#page").text(page);
    }
}

function nextPage(){
    var page=$("#page").text();
    page=page-0+1;
    if((page-1)*10>=articleSum){
        alert("已是最后一页");
    }
    else{
        $("#lastOne").html("");
        LoadPage(page);
        $("#page").text(page);
    }
}

//获取网站信息
function getSiteInfoDetail() {
    getSiteInfo();
    setLastUpdateTime();
    getRunningTime();
}


//网站信息
function getSiteInfo() {

    $.ajax({
        type: 'GET',
        url: '/getSiteInfo',
        dataType: 'json',
        data: {
        },
        success: function (data) {
            console.log(data);
            $('#articleNum').html(data.articleNum);
            $('#categoryNum').html(data.categoryNum);
            $('#labelNum').html(data.labelNum);
            // 侧边栏
            $('#slideArticleNum').html(data.articleNum);
            $('#slideCategoryNum').html(data.categoryNum);
            $('#slideLabelNum').html(data.labelNum);
        },
        error: function () {
            alert("获取网站信息失败！");
        }
    });

}

//网站最后更新时间
function setLastUpdateTime() {
    $('.siteUpdateTime').html(siteLastUpdateTime);
}


//网站运行时间
//beginTime为建站时间的时间戳
function siteRunningTime(time) {
    var theTime;
    var strTime = "";
    if (time >= 86400){
        theTime = parseInt(time/86400);
        strTime += theTime + "天";
        time -= theTime*86400;
    }
    if (time >= 3600){
        theTime = parseInt(time/3600);
        strTime += theTime + "时";
        time -= theTime*3600;
    }
    if (time >= 60){
        theTime = parseInt(time/60);
        strTime += theTime + "分";
        time -= theTime*60;
    }
    strTime += time + "秒";

    $('.siteRunningTime').html(strTime);

    //console.log(strTime);
}

function getRunningTime() {
    var nowDate = new Date().getTime();
    //网站开始运行日期
    var oldDate = new Date(siteBeginRunningTime.replace(/-/g,'/'));
    var time = oldDate.getTime();
    var theTime = parseInt((nowDate-time)/1000);
    setInterval(function () {
        siteRunningTime(theTime);
        theTime++;
    },1000);
}

