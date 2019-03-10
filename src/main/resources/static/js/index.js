$(function(){
    alert("你好呀！");
    //haha();
    //LoadIndex();
});

function haha()
{
    alert("1111111111111");
}

function LoadIndex() {
    $.ajax({
        url: "/summarys",
        type: "POST",
        async: false,
        data: {},

        success: function (data) {

            console.log("成功了！");

            var content = "";

            for (var i in data) {
                var id = data[i].id;
                var topic = data[i].topic;
                var summary = data[i].summary;
                var articleId = data[i].articleId;

                content = content +
                    '<div class="col-xs-6 col-lg-4" id="lastOne">' +
                    '<h2>' + topic + '</h2>' +
                    '<p>' + summary + '</p>' +
                    '<p><a class="btn btn-default" href="' + 'javascript:GetArticleById(' + articleId + ');' + '" role="button">View details &raquo;</a></p>' +
                    '</div>';
            }


            $("#lastOne").html(content);

        }, error: function () {
            alert("数据加载错误");
        }

    });
}

    function GetArticleById(id)
    {
        $.ajax({
            url:"/article",
            type:"POST",
            async:false,
            data:{id:id},

            success:function(data){
                    console.log("成功了！");
                    var content = "";
                    var id = data.id;
                    var hi = data.content;

                    content = content + '<div class="col-xs-6 col-lg-4">'+
                        '<h2>'+id+'</h2>'+
                        '<p>'+hi+'</p>'+
                    '</div>';

                    $("#lastOne").html(content);
            },error:function(){
                alert("数据加载错误");
            }

        });



    }