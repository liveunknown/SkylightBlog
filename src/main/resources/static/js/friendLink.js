$(function(){
    LoadFamousFriendLinks();
    LoadNormalFriendLinks();
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
                    '                       <i class="am-icon-street-view "></i>'+
                    '                       <a href=" '+ url +' " target="_blank">' + blogger + '</a>'+
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
                    '                       <i class="am-icon-street-view "></i>'+
                    '                       <a href=" '+ url +' " target="_blank">' + blogger + '</a>'+
                    '                   </div>';
            }

            $("#normal-friend").html(content);
        }, error: function () {
            alert("请求友链时出现错误！");
        }

    });
}