var articleSum;
var chosenCategoryId = 0;

$(function(){
    LoadCategories();
    LoadArticleInfo(1);
    console.log("Original value is: "+chosenCategoryId);
    getArticleSum();
});

function LoadCategories() {
    $.ajax({
        url: "/categories",
        type: "GET",
        async: false,
        data: {},
        success: function (data) {
            console.log("成功了！");
            var content = "";

            var id;
            var name;
            var number;
            var createTime;
            for(var i in data)
            {
              id = data[i].id;
              name = data[i].name;
              content = content +'<li><a href="javascript:LoadArticleInfoByCategoryId(1,'+ id +');">'+ name +'</a></li>';
            }

            $("#category").html(content);

        }, error: function () {
            alert("数据加载错误");
        }
    });
}


function LoadArticleInfo(page) {
    $.ajax({
        url: "/articleInfoDetailsByCategoryId",
        type: "GET",
        async: false,
        data: {page:page},

        success: function (data) {
            console.log("成功了！");

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

                content = content +
                    '<div class="col-xs-12 col-lg-12 shadow distance">'+
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
            }


            $("#lastOne").html(content);

        }, error: function () {
            alert("数据加载错误");
        }

    });
}

function LoadArticleInfoByCategoryId(page,id) {
    chosenCategoryId = id;
    console.log("The chosen one is: "+chosenCategoryId);
    $("#page").text(1);
    getArticleSumByCategoryId(id);

    $.ajax({
        url: "/articleInfoDetailsByCategoryId",
        type: "GET",
        async: false,
        data: {page:page,categoryId:id},

        success: function (data) {
            console.log("成功了！");

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

                content = content +
                    '<div class="col-xs-12 col-lg-12 shadow distance">'+
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
            }


            $("#lastOne").html(content);

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
            console.log("成功了！");
            articleSum = data;
            console.log(articleSum);
            $("#sum").text(articleSum);
        }, error: function () {
            alert("数据加载错误");
        }
    });
}

function getArticleSumByCategoryId(id) {
    $.ajax({
        url: "/articleSumByCategoryId",
        type: "GET",
        async: false,
        data: {categoryId:id},
        success: function (data) {
            console.log("成功了！");
            articleSum = data;
            console.log(articleSum);
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
        if(chosenCategoryId==0)
        {
            LoadArticleInfo(page);
        }
        else {
            LoadArticleInfoByCategoryId(page,chosenCategoryId);
        }

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
        if(chosenCategoryId==0)
        {
            LoadArticleInfo(page);
        }
        else {
            LoadArticleInfoByCategoryId(page,chosenCategoryId);
        }
        $("#page").text(page);
    }
}