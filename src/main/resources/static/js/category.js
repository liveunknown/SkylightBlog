var articleSum;
var chosenCategoryId = 0;

$(function(){
    LoadCategories();
    LoadArticleInfo(1);
    console.log("Original value is: "+chosenCategoryId);
    getArticleSum();
    getSiteInfo();
});

function LoadCategories() {
    $.ajax({
        url: "/categories",
        type: "GET",
        async: false,
        data: {},
        success: function (data) {
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
            var content = "";
            var labels = "";

            for (var i in data) {
                var id = data[i].articleInfoId;
                var createTime = data[i].createBy;
                var category = data[i].category.name;
                var Original = data[i].isOriginal;
                var title = data[i].title;
                var author = data[i].author;
                var summary = data[i].summary;
                var imageUrl = data[i].imageUrl;

                var original = isOriginal(Original);

                for(var j=0;j<data[i].articleLabelList.length;j++)
                {
                    labels = labels + '<span class="glyphicon glyphicon-paperclip"></span> ' + data[i].articleLabelList[j].label.name + '&#8194;';
                }

                content =
                    '<div class="articleDiv">'+
                    '<article class="am-g blog-entry-article">'+
                    '                    <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-img">'+
                    '                        <img src="' + imageUrl + '" alt="" class="am-u-sm-12">'+
                    '                    </div>'+
                    '                    <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-text" style="font-size: 15px">'+
/*                    '                        <span><a href="" class="blog-color">' + original + '&nbsp;</a></span>'+*/
                                                         original +
                    '                        <span style="font-family:Microsoft YaHei">' + category + ' &nbsp;</span>'+
                    '                        <span>'+ createTime +'</span>'+
                    '                        <h3 style="margin-top: 10px"><b><a href="/article.html?id='+ id +'">'+ title +'</a></b></h3>'+
                    '                        <p style="font-size: 1.5rem; margin-top: 15px;font-family:Microsoft YaHei">'+ summary +
                    '                        </p>'+
                    '                        <p><a href="" class="blog-continue">continue reading</a></p>'+
                    '                    </div>'+
                    '                </article>'+
                    '</div>';
                labels = "";

                $("#lastOne").append(content);
            }

            $('.articleDiv').scrollspy({
                animation: 'slide-left'
            })

            //$("#lastOne").html(content);

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

    $("#lastOne").html("");

    $.ajax({
        url: "/articleInfoDetailsByCategoryId",
        type: "GET",
        async: false,
        data: {page:page,categoryId:id},

        success: function (data) {
            var content = "";
            var labels = "";

            for (var i in data) {
                var id = data[i].articleInfoId;
                var createTime = data[i].createBy;
                var category = data[i].category.name;
                var Original = data[i].isOriginal;
                var title = data[i].title;
                var author = data[i].author;
                var summary = data[i].summary;
                var imageUrl = data[i].imageUrl;

                var original = isOriginal(Original);

                for(var j=0;j<data[i].articleLabelList.length;j++)
                {
                    labels = labels + '<span class="glyphicon glyphicon-paperclip"></span> ' + data[i].articleLabelList[j].label.name + '&#8194;';
                }

                content =
                    '<div class="articleDiv">'+
                    '<article class="am-g blog-entry-article">'+
                    '                    <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-img">'+
                    '                        <img src="' + imageUrl + '" alt="" class="am-u-sm-12">'+
                    '                    </div>'+
                    '                    <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-text" style="font-size: 15px">'+
/*                    '                        <span><a href="" class="blog-color">' + original + '&nbsp;</a></span>'+*/
                                                         original +
                    '                        <span style="font-family:Microsoft YaHei">' + category + ' &nbsp;</span>'+
                    '                        <span>'+ createTime +'</span>'+
                    '                        <h3 style="margin-top: 10px"><b><a href="/article.html?id='+ id +'">'+ title +'</a></b></h3>'+
                    '                        <p style="font-size: 1.5rem; margin-top: 15px; font-family:Microsoft YaHei">'+ summary +
                    '                        </p>'+
                    '                        <p><a href="" class="blog-continue">continue reading</a></p>'+
                    '                    </div>'+
                    '                </article>'+
                    '</div>';
                labels = "";

                $("#lastOne").append(content);
            }

            $('.articleDiv').scrollspy({
                animation: 'slide-left'
            })

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

function getArticleSumByCategoryId(id) {
    $.ajax({
        url: "/articleSumByCategoryId",
        type: "GET",
        async: false,
        data: {categoryId:id},
        success: function (data) {

            articleSum = data;
            console.log("该分类下的文章数量为：" + articleSum);
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
        $("#lastOne").html("");
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

//侧边栏网站信息
function getSiteInfo() {

    $.ajax({
        type: 'GET',
        url: '/getSiteInfo',
        dataType: 'json',
        data: {
        },
        success: function (data) {
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

function isOriginal(isOriginal) {

    var original;

    if(isOriginal == '1')
    {
        //original = "原创";
        original = '<span class="am-badge am-badge-success am-radius">'+ '原创' +'</span>&#8194;'
    } else if (isOriginal == '0') {
        //original = "转载";
        original = '<span class="am-badge am-badge-secondary am-radius">'+ '转载' +'</span>&#8194;'
    }

    return original;
}