$(function(){

    alert("你好呀！");
    haha();
    LoadIndex();
});

function haha()
{
    alert("1111111111111");
}

function LoadIndex()
{
    $.ajax({
        url:"/summarys",
        type:"POST",
        async:false,
        data:{},

        success:function(data){

            console.log("成功了！");

            var content = "";

            for(var i in data)
            {
                var id = data[i].id;
                var topic = data[i].topic;
                var summary = data[i].summary;
                var articleId = data[i].articleId;

               content = content+
                   '<div class="col-xs-6 col-lg-4" id="lastOne">'+
                   '<h2>'+topic+'</h2>'+
                   '<p>'+summary+'</p>'+
                   '<p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>'+
                   '</div>';
            }


            $("#lastOne").append(content);

        },error:function(){
            alert("数据加载错误");
        }

    });
}