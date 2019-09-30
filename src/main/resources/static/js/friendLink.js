$(function(){
    LoadFamousFriendLinks();
    LoadNormalFriendLinks();
    getSiteInfo();
});

function LoadFamousFriendLinks() {
    $.ajax({
        url: "/getFriendLinkList",
        type: "Get",
        async: false,
        data: {isFamous:1},
        success: function (data) {
            var content = "";
            var blogger;
            var url;

            for(var i in data)
            {
                blogger = data[i].blogger;
                url = data[i].url;

                content = content + '<div class="friendLink">'+
                    '                       <i class="am-icon-car "></i>'+
                    '                       <a href="http://'+ url +' " target="_blank">' + blogger + '</a>'+
                    '                   </div>';
            }

            $("#famous-friend").html(content);
        }, error: function () {
            alert("请求友链时出现错误！");
        }

    });
}

function LoadNormalFriendLinks() {
    $.ajax({
        url: "/getFriendLinkList",
        type: "Get",
        async: false,
        data: {isFamous:0},
        success: function (data) {
            var content = "";
            var blogger;
            var url;

            for(var i in data)
            {
                blogger = data[i].blogger;
                url = data[i].url;

                content = content + '<div class="friendLink">'+
                    '                       <i class="am-icon-bicycle "></i>'+
                    '                       <a href="http://'+ url +' " target="_blank">' + blogger + '</a>'+
                    '                   </div>';
            }

            $("#normal-friend").html(content);
        }, error: function () {
            alert("请求友链时出现错误！");
        }

    });
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